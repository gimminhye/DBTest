import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//]JDBC를 활용한 ORACLE 서버 연결
public class Main {
	public static void main(String[] args) {
		//tcp는 연결설정->데이터전송(sql,결과)->연결해제
		Connection conn = null;
		Statement stmt = null; //sql질의문을 던져서 결과를 받을때 질의문을 던지는 객체!
		ResultSet rs = null; //질의문이  select 명령일때 이 객체가 반환값을 처리
		
		
		
		String url ="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user ="madang";
		String password ="madang";
		
		//new를 사용하지 않고 수동으로 클래스 자체를 메모리에 올리는 법
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url, user, password);
			
			if(conn!=null){	
				//System.out.println("연결성공");
				stmt = conn.createStatement();
				
				String sql = "select * from book";
				rs =stmt.executeQuery(sql); //rs가 커서를 포함. next 를 시켜서 endofrecode 가 나오면 false
				
				while(rs.next()){
					int bnum = rs.getInt(1); //컬럼이름을 "bookid"같이 써줘도 대고 컬럼 번호를 써도됨
					String bname = rs.getString(2);
					String publisher = rs.getString(3);
					int bprice = rs.getInt(4);
					
					//커서는 한번 지나가면 다시 못올라감. 그래서 한번 컬럼에 커서가 도착했을때 읽을 수 있는건 다 읽어야함
					System.out.print("\t"+bnum);
					System.out.print("\t"+bname);
					System.out.print("\t\t\t"+publisher);
					System.out.println("\t"+bprice);
				}
				
				
			}
			
				
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn !=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
	}
};
