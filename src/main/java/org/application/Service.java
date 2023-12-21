package org.application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Service {

    private static Connection connection=null;

    static {
        String url="jdbc:mysql://localhost:3306/j2ee";
        String user="root";
        String pass="Redminote5@";
        try {
            connection= DriverManager.getConnection(url,user,pass);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }


    public int insertProdct(Product newProduct) {
        int n=0;
        String query="Insert into Product values(?,?,?,?)";
        try {
            PreparedStatement psmt=connection.prepareStatement(query);
            psmt.setInt(1,newProduct.getProductId());
            psmt.setString(2, newProduct.getProductName());
            psmt.setDouble(3,newProduct.getProductPrice());
            psmt.setString(4,newProduct.getProductType());

            n=psmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
            }
        return n;
    }
    public int deleteOperation(int prodId){
        int n=0;
        String query="delete from product where prodId=?";
        try {
            PreparedStatement psmt=connection.prepareStatement(query);
            psmt.setInt(1,prodId);
           n= psmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return n;
    }

    public int updateOperation( Product product) {
        int n=0;
        String query="Update product set prodPrice=? ,prodName=?,prodType=? where prodId=?";

        try {
            PreparedStatement  psmt = connection.prepareStatement(query);
            psmt.setDouble(1,product.getProductPrice());
            psmt.setString(2,product.getProductName());
            psmt.setString(3, product.getProductType());
            psmt.setInt(4,product.getProductId());
            n= psmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }

        return n;

    }
    public List<Product> displayAllProduct(){
        List<Product> productList=new ArrayList<>();
        String displayQuery="Select * from Product";

        try {
            Statement stmt=connection.createStatement();
            ResultSet rs= stmt.executeQuery(displayQuery);

            while (rs.next()){
                int prodId=rs.getInt(1);
                String prodName=rs.getString(2);
                double prodPrice=rs.getDouble(3);
                String prodType=rs.getString(4);

                Product product=new Product(prodId,prodName,prodPrice,prodType);
                productList.add(product);
            }

        } catch (SQLException e) {

        }
    return productList;
    }

    public Product getProdById(int id) {
        Product product=null;
        String query="select * from product where prodId=?";
        try {
            PreparedStatement psmt=connection.prepareStatement(query);
            psmt.setInt(1,id);
           ResultSet rs= psmt.executeQuery();
           rs.next();
           String prodName=rs.getString(2);
           double prodPrice=rs.getDouble(3);
           String prodType=rs.getString(4);
           product=new Product(id,prodName,prodPrice,prodType);
        } catch (SQLException e) {
            System.out.println("Invalid Product ID or Product not found");
        }
        return product;
    }

    public List<Product> diplayByPrice(double prodPrice1,double prodPrice2) {
        List<Product> productList=new ArrayList<>();
        String query="Select * from Product where prodPrice between ? and ?";
        try {
            PreparedStatement psmt=connection.prepareStatement(query);
            psmt.setDouble(1,prodPrice1);
            psmt.setDouble(2,prodPrice2);
          ResultSet rs=  psmt.executeQuery();
          while (rs.next()){
              int prodId=rs.getInt(1);
              String prodName=rs.getString(2);
              double pPrice=rs.getDouble(3);
              String prodType=rs.getString(4);

              Product product=new Product(prodId,prodName,pPrice,prodType);
              productList.add(product);
          }
        } catch (SQLException e) {
            System.out.println(e);
        }

            return productList;
    }

    public List<Product> getProdByCategory(String prodType) {
        List<Product> productList=new ArrayList<>();

        String query="Select * from product where prodType=?";
        try {
            PreparedStatement psmt=connection.prepareStatement(query);
            psmt.setString(1,prodType);
            ResultSet rs=psmt.executeQuery();
            while (rs.next()){
                int prodId=rs.getInt(1);
                String prodName=rs.getString(2);
                double pPrice=rs.getDouble(3);
                String Type=rs.getString(4);

                Product product=new Product(prodId,prodName,pPrice,prodType);
                productList.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return  productList;
    }
}
