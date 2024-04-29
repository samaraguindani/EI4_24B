package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class UserDao {

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:\\Program Files\\SQLite\\lencol_db.db");

      
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return con;
    }
    
   public static int salvar(Usuario usuario) {
    int status = 0;
    try {
        Connection con = getConnection();
        if (con != null) {
            Statement ps = con.createStatement();
            String encryptedPassword = hashSenha(usuario.getPassword());
            // Corrigir a declaração SQL de inserção
            status = ps.executeUpdate("insert into usuarios(username, password) values('" + usuario.getUsername() + "','" + encryptedPassword + "')");
            con.close(); // Fechar a conexão após o uso
        } else {
            System.out.println("Erro: Conexão com o banco de dados não foi obtida com sucesso.");
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return status;
}


    
    public static int atualizar(Usuario usuario) {
        int status = 0;
        try {
            Connection con = getConnection();
            Statement ps = con.createStatement();
            status = ps.executeUpdate("update usuarios set username='" + usuario.getUsername() + "',password='" + usuario.getPassword() + "'");


        } catch (SQLException e) {
            System.out.println(e);
        }
        return status;
    }

    public static int deletar(Usuario usuario) {
        int status = 0;
        try {
            Connection con = getConnection();
            Statement ps = con.createStatement();
            status = ps.executeUpdate("delete from usuarios where id=" + usuario.getId());

        } catch (SQLException e) {
            System.out.println(e);
        }
        return status;
    }
    
    public static String hashSenha( String senha )
    {
        try 
        {
            MessageDigest md        = MessageDigest.getInstance( "SHA-256" );
            byte[]        hash_bytes = md.digest( senha.getBytes() );
            
            return Base64.getEncoder().encodeToString(hash_bytes);
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public static List<Usuario> lerTudo() {
        List<Usuario> list = new ArrayList<Usuario>();

        try {
            Connection con = getConnection();
            Statement ps = con.createStatement();

            ResultSet rs = ps.executeQuery("select * from usuarios");
            
            //loop para repetir o resultset e preencher objeto do usuario
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                list.add(usuario);
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public static Usuario getReceitaPorId(int id) {
        Usuario usuario = null;
        try {
            Connection con = getConnection();
            Statement ps = con.createStatement();

            ResultSet rs = ps.executeQuery("select * from usuarios where id =" + id);
            
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
            }
            
        } catch (SQLException e) {
            System.out.println(e);
        }
        return usuario;
    }
    
    public static boolean verificarCredenciais(String username, String password) {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios WHERE username='" + username + "'");
            if (rs.next()) {
                // Se o usuário existir, verificar a senha
                String hashedPassword = rs.getString("password");
                String encryptedPassword = hashSenha(password);
                return hashedPassword.equals(encryptedPassword);
            } else {
                // Se o usuário não existir, retornar false
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
}
