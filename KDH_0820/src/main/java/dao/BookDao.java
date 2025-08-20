package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import dto.Book;

public class BookDao {
	DataSource ds;
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	public List<Book>selectList() throws Exception{
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String query = null;
		try {
			con = ds.getConnection();
			query = "select * from book order by id desc";
			st=con.createStatement();
			rs=st.executeQuery(query);
			ArrayList<Book> books = new ArrayList<Book>();
			while(rs.next()) {
				Book b = new Book();
				//id title author category publisher price 
				b.setId(rs.getInt("id"));
				b.setTitle(rs.getString("title"));
				b.setAuthor(rs.getString("author"));
				b.setCategory(rs.getString("category"));
				b.setPublisher(rs.getString("publisher"));
				b.setPrice(rs.getInt("price"));
				books.add(b);
			}
			return books;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			try {if(rs != null)rs.close();} catch (Exception e) {}
			try {if(st != null)st.close();} catch (Exception e) {}
			try {if(con != null)con.close();} catch (Exception e) {}
		}
	}
	public int insert(Book book) throws Exception{
		Connection con = null;
		PreparedStatement pst = null;
		String query = null;
		try {
			con = ds.getConnection();
			query = "insert into book (title,author,publisher,pub_date,isbn,category,price,stock,cre_date,mode_date,memo) values (?,?,?,?,?,?,?,?,now(),now(),?)";
			pst = con.prepareStatement(query);
			pst.setString(1,book.getTitle());
			pst.setString(2,book.getAuthor());
			pst.setString(3,book.getPublisher());
			pst.setDate(4, new java.sql.Date(book.getPub_date().getTime()));
			pst.setString(5,book.getIsbn());
			pst.setString(6,book.getCategory());
			pst.setInt(7,book.getPrice());
			pst.setInt(8,book.getStock());
			pst.setString(9,book.getMemo());
			return pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			try {if(pst != null)pst.close();} catch (Exception e) {}
			try {if(con != null)con.close();} catch (Exception e) {} //자원 반납을
		}
	}
	public Book selectOne(int id) throws Exception{
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String query = null;
		try {
			con = ds.getConnection();
			query = "select * from book where id =?";
			pst = con.prepareStatement(query);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			if(rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				book.setPub_date(rs.getDate("pub_date"));
				book.setIsbn(rs.getString("isbn"));
				book.setCategory(rs.getString("category"));
				book.setPrice(rs.getInt("price"));
				book.setStock(rs.getInt("stock"));
				book.setCre_date(rs.getDate("cre_date"));
				book.setMode_date(rs.getDate("mode_date"));
				book.setMemo(rs.getString("memo"));
				return book;
			}
			else {
				throw new Exception("해당 도서 없음");
			}
		} catch (Exception e) {
			throw e;
		}
		finally {
			try {if(rs != null)rs.close();} catch (Exception e) {}
			try {if(pst != null)pst.close();} catch (Exception e) {}
			try {if(con != null)con.close();} catch (Exception e) {}
		}
	}
	public int update(Book book) throws Exception{
		Connection con = null;
		PreparedStatement pst = null;
		String query = null;
		try {
			con= ds.getConnection();
			query = "update book set title =?, author=?, publisher=?, pub_date=?, isbn=?, category=?, price=?, stock=?, memo=? where id=?";
			pst = con.prepareStatement(query);//9 10
			pst.setString(1, book.getTitle());
			pst.setString(2, book.getAuthor());
			pst.setString(3, book.getPublisher());
			pst.setDate(4, new java.sql.Date(book.getPub_date().getTime()));
			pst.setString(5, book.getIsbn());
			pst.setString(6, book.getCategory());
			pst.setInt(7, book.getPrice());
			pst.setInt(8, book.getStock());
			pst.setString(9, book.getMemo());
			pst.setInt(10, book.getId());
			return pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			try {if(pst != null)pst.close();} catch (Exception e) {}
			try {if(con != null)con.close();} catch (Exception e) {}
		}
	}
	public int delete(int id) throws Exception{
		Connection con = null;
		PreparedStatement pst = null;
		String query = null;
		try {
			con = ds.getConnection();
			query ="delete from book where id=?";
			pst = con.prepareStatement(query);
			pst.setInt(1, id);
			return pst.executeUpdate();
		} catch (Exception e) {
			throw e;
		}finally {
			try {if(pst != null)pst.close();} catch (Exception e) {}
			try {if(con != null)con.close();} catch (Exception e) {}
		}
	}
	public List<Book> searchBook(String title)throws Exception {
		Connection con = null; //커넥션풀에서 받기위한 con
		PreparedStatement pst = null;
		ResultSet rs = null;
		String query = null;
		try {
			con = ds.getConnection();
			query = "select * from book where title like ?";
			pst = con.prepareStatement(query);
			pst.setString(1, "%" + title + "%");
			ArrayList<Book> books = new ArrayList<Book>();
			rs= pst.executeQuery();
			while(rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt("id"));
				b.setTitle(rs.getString("title"));
				b.setAuthor(rs.getString("author"));
				b.setCategory(rs.getString("category"));
				b.setPublisher(rs.getString("publisher"));
				b.setPrice(rs.getInt("price"));
				books.add(b);
			}
			return books;
		} catch (Exception e) {
			throw e;
		}finally {
			try {if(rs != null)rs.close();} catch (Exception e) {}	
			try {if(pst != null)pst.close();} catch (Exception e) {}
			try {if(con != null)con.close();} catch (Exception e) {}
		}		
	}
	
}
