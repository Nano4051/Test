package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import dto.Member;

public class MemberDao {
	DataSource ds;//데이터 소스를 받음 con을 데이터소스에서 받음
	public void setDataSource(DataSource ds) {
		this.ds =ds;
	}
	public List<Member> selectList() throws Exception{ //전체 목록 출력 리턴값은 리스트형 member로 받아야함
		Connection con = null; //커넥션풀에서 받기위한 con
		Statement st = null;
		ResultSet rs = null;
		String query = null;
		try {
			con = ds.getConnection();//데이터 소스에서 커넥션한개를 받아옴 메소드 종료할때 반납을 해야된다
			query = "select * from members";
			st = con.createStatement();
			rs = st.executeQuery(query);
			ArrayList<Member> members = new ArrayList<>();
			while(rs.next()) {
				Member m = new Member();
				m.setMno(rs.getInt("mno"));
				m.setMname(rs.getString("mname"));
				m.setEmail(rs.getString("email"));
				m.setCre_date(rs.getDate("cre_date"));
				members.add(m);
			}
			return members; //리턴만 시켜주면 됨 원래는 request로 넘겨야됨
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			try {if(rs != null)rs.close();} catch (Exception e) {}
			try {if(st != null)st.close();} catch (Exception e) {}
			try {if(con != null)con.close();} catch (Exception e) {} //자원 반납을 꼭 ds에 해줘야 한다 
		}
	}
	public int insert(Member member) throws Exception{
		Connection con = null; //커넥션풀에서 받기위한 con
		PreparedStatement pst = null;
		String query = null;
		try {
			con = ds.getConnection();//데이터 소스에서 커넥션한개를 받아옴
			query = "insert into members (email,pwd,mname,cre_date,mod_date) values(?,?,?,now(),now())";
			pst = con.prepareStatement(query);
			pst.setString(1, member.getEmail()); // 첫번째 ? 자리에 들어갈 전달값
			pst.setString(2, member.getPwd()); // 두번째 ? 
			pst.setString(3, member.getMname()); // 세번째 ? 
			return pst.executeUpdate(); //매개변수 없이 executeUpdate호출 C U D는 모두 이 메소드 사용
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			try {if(pst != null)pst.close();} catch (Exception e) {}//resultSet 대신에 pst가 접속여부로 사용됨
			try {if(con != null)con.close();} catch (Exception e) {}
		}
	}
	public int update(Member member) throws Exception {//한 줄의 데이터를 불러와야 수정가능
		Connection con = null; //커넥션풀에서 받기위한 con
		PreparedStatement pst = null;
		String query = null;
		try {
			con = ds.getConnection();//데이터 소스에서 커넥션한개를 받아옴
			query = "update members set mname=?, email=?, mod_date=now() where mno =?";
			pst =con.prepareStatement(query);
			pst.setString(1, member.getMname());
			pst.setString(2, member.getEmail());
			pst.setInt(3, member.getMno());
			return pst.executeUpdate(); //
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			try {if(pst != null)pst.close();} catch (Exception e) {}
			try {if(con != null)con.close();} catch (Exception e) {}
		}
	}
	public Member selectOne(int mno) throws Exception{//수정하려면 필요 pk 가져옴
		Connection con = null; //커넥션풀에서 받기위한 con
		PreparedStatement pst = null;
		ResultSet rs = null;
		String query = null;
		try {
			con = ds.getConnection();//데이터 소스에서 커넥션한개를 받아옴
			query = "select * from members where mno = ?";
			pst=con.prepareStatement(query);
			pst.setInt(1,mno);
			rs=pst.executeQuery();
			if(rs.next()) {
				Member member = new Member();
				member.setMno(rs.getInt("mno"));
				member.setMname(rs.getString("mname"));
				member.setEmail(rs.getString("email"));
				member.setCre_date(rs.getDate("cre_date"));
				member.setMod_date(rs.getDate("mod_date"));
				return member;
			}else {
				throw new Exception("해당번호의 회원은 찾을수 없습니다");
			}
		} catch (Exception e) {
			throw e;
		}finally {
			try {if(rs != null)rs.close();} catch (Exception e) {}
			try {if(rs != null)pst.close();} catch (Exception e) {}
			try {if(con != null)con.close();} catch (Exception e) {}
		}
	}
	public int delete(int mno) throws Exception{
		Connection con = null; //커넥션풀에서 받기위한 con
		PreparedStatement pst = null;
		String query = null;
		try {
			con = ds.getConnection();//데이터 소스에서 커넥션한개를 받아옴
			query = "delete from members where mno =?";
			pst = con.prepareStatement(query);
			pst.setInt(1, mno);
			return pst.executeUpdate();
		} catch (Exception e) {
			throw e;
		}finally{
			try {if(pst != null)pst.close();} catch (Exception e) {}
			try {if(con != null)con.close();} catch (Exception e) {}
		}
	}
	public Member login(Member member) throws Exception{
		Connection con = null; //커넥션풀에서 받기위한 con
		ResultSet rs = null; //결과 필요함
		PreparedStatement pst = null;
		String query = null;
		try {
			con = ds.getConnection();//데이터 소스에서 커넥션한개를 받아옴
			query = "select * from members where email=? and pwd=?"; //sql문 작성
			pst = con.prepareStatement(query);
			pst.setString(1, member.getEmail()); // 첫번째 ? 자리에 들어갈 전달값
			pst.setString(2, member.getPwd()); // 두번째 ?
			rs = pst.executeQuery();
			if(rs.next()) {
				Member loginmember = new Member();
				loginmember.setEmail(rs.getString("email"));
				loginmember.setPwd(rs.getString("pwd"));
				loginmember.setMname(rs.getString("mname"));
				return loginmember;
			}else {
				return null; //null을 리턴하면 로그인 서블릿에서 널값을 받아서 로그인 실패 화면을 출력해줌
			}
		} catch (Exception e) {
			throw e;
		}finally {
			try {if(pst != null)pst.close();} catch (Exception e) {}
			try {if(pst != null)rs.close();} catch (Exception e) {}	
			try {if(con != null)con.close();} catch (Exception e) {}
		}
	}	
}