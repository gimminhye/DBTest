import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//]JDBC�� Ȱ���� ORACLE ���� ����
public class Main {
	public static void main(String[] args) {
		//tcp�� ���ἳ��->����������(sql,���)->��������
		Connection conn = null;
		Statement stmt = null; //sql���ǹ��� ������ ����� ������ ���ǹ��� ������ ��ü!
		ResultSet rs = null; //���ǹ���  select ����϶� �� ��ü�� ��ȯ���� ó��
		
		
		
		String url ="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String user ="madang";
		String password ="madang";
		
		//new�� ������� �ʰ� �������� Ŭ���� ��ü�� �޸𸮿� �ø��� ��
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(url, user, password);
			
			if(conn!=null){	
				//System.out.println("���Ἲ��");
				stmt = conn.createStatement();
				
				String sql = "select * from book";
				rs =stmt.executeQuery(sql); //rs�� Ŀ���� ����. next �� ���Ѽ� endofrecode �� ������ false
				
				while(rs.next()){
					int bnum = rs.getInt(1); //�÷��̸��� "bookid"���� ���൵ ��� �÷� ��ȣ�� �ᵵ��
					String bname = rs.getString(2);
					String publisher = rs.getString(3);
					int bprice = rs.getInt(4);
					
					//Ŀ���� �ѹ� �������� �ٽ� ���ö�. �׷��� �ѹ� �÷��� Ŀ���� ���������� ���� �� �ִ°� �� �о����
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
