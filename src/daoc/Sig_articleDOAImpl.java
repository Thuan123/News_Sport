package daoc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import crawler.thread.SSHConnect;
import daoc.connectionpool.DBPool;
import newsport.Sig_article;

public class Sig_articleDOAImpl implements Sig_articleDOA {
	public Connection conn;
	
	/*
	 * 
	 * Khoi tao
	 * 
	 */
	public Sig_articleDOAImpl() {
		if(DBPool.pool.isEmpty()){
			DBPool.build(100);
		}
		conn = DBPool.getConnection();

	}
	/*
	 * 
	 * Them data vao co so du lieu
	 * 
	 */

	@Override
	public void addSig(Sig_article p) {
		try {
			String query = " insert into sig_article (url,title,abstract,text_content,extracted_content,html_content,cover_url,published,crawled,wid,cid,meta)"
					+ " values (?, ?, ?, ?, ?,?,?,?,?,?,?,?)";
			// create the mysql insert preparedstatement
			PreparedStatement prepared = conn.prepareStatement(query);

			prepared.setString(1, p.getUrl());
			prepared.setString(2, p.getTitle());
			prepared.setString(3, p.getAbstract_content());
			prepared.setString(4, p.getText_content());
			prepared.setString(5, p.getExtracted_content());
			prepared.setString(6, p.getHtml_content());
			prepared.setString(7, p.getCover_url());
			prepared.setDate(8, p.getPublished());
			prepared.setDate(9, p.getCrawled());
			prepared.setInt(10, p.getWid());
			prepared.setInt(11, p.getCid());
			prepared.setString(12, p.getMeta());
			prepared.execute();
			
			// conn.close();
			 //DBConnect.session.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<Sig_article> getList() {
		// DBConnect.session.disconnect();
		// TODO Auto-generated method stub
		try {
			// conn=DBConnect.getConnection();
			List<Sig_article> list = new ArrayList<Sig_article>();
			String sql = "select * from sig_article";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));

			}
			rs.close();
			ps.close();
			// conn.close();
			// DBConnect.session.disconnect();
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
public void addCategory(){
		 
			 try { conn=DBConnect.getConnection(); } catch (SQLException e) {
			 e.printStackTrace(); }
			
			try {
				String query = " insert into sig_category (name,abstract,url,icon_url,wid)"
						+ " values (?, ?, ?, ?, ?)";
				// create the mysql insert preparedstatement
				PreparedStatement prepared = conn.prepareStatement(query);

				//prepared.setInt(1, 3);
				prepared.setString(1, "Football");
				prepared.setString(2, null);
				prepared.setString(3, "http://www1.skysports.com/football/news");
				prepared.setString(4, null);
				prepared.setInt(5, 1);
				prepared.execute();
				
				conn.close();
				DBConnect.session.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	 }
	 
	 public void RemoveCategory(){
		// TODO Auto-generated method stub
			try {
				 conn=DBConnect.getConnection();
				String sql = "TRUNCATE sig_category";
				PreparedStatement ps = (PreparedStatement) conn
						.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				 conn.close();
				 DBConnect.session.disconnect();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
	 }
	 @Override
	 public List<Sig_article> removeDuplicateSig(List<Sig_article> arrList)
	 {
	     @SuppressWarnings("unchecked")
		 HashSet<Sig_article> h = new HashSet<Sig_article>(arrList);
	     arrList.clear();
	     arrList.addAll(h);
	    
	     return arrList;
	                    
	 }
	 @SuppressWarnings({ "unchecked", "unchecked" })
	 @Override
	 public List<String> removeDuplicate(List<String> arrList)
	 {
	     @SuppressWarnings("unchecked")
		 HashSet<String> h = new HashSet<String>(arrList);
	     arrList.clear();
	     arrList.addAll(h);
	    
	     return arrList;
	                    
	 }
	@Override
	 public List<Sig_article> getRanRelatedNews(){
		 // TODO Auto-generated method stub
			try {
				// conn=DBConnect.getConnection();
				List<Sig_article> list = new ArrayList<Sig_article>();
				String sql = "select * from sig_article order by rand()  limit 50";
				PreparedStatement ps = (PreparedStatement) conn
						.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					int id = rs.getInt("id");
					String url = rs.getString("url");
					System.out.println(url);
					String title = rs.getString("title");
					String abstract_content = rs.getString("abstract");
					String text_content = rs.getString("text_content");
					String extracted_content = rs.getString("extracted_content");
					String html_content = rs.getString("html_content");
					String cover_url = rs.getString("cover_url");
					java.sql.Date published = rs.getDate("published");
					java.sql.Date crawled = rs.getDate("crawled");
					int wid = rs.getInt("wid");
					int cid = rs.getInt("cid");
					String meta = rs.getString("meta");
					list.add(new Sig_article(id, url, title, abstract_content,
							text_content, extracted_content, html_content,
							cover_url, published, crawled, wid, cid, meta));

				}
				rs.close();
				ps.close();
				// conn.close();
				// DBConnect.session.disconnect();
				return list;

			} catch (SQLException e) {
				// DBConnect.session.disconnect();
				e.printStackTrace();
			}

			return null;
	 }
	
	@Override
	public List<Sig_article> getRan() {
		 // TODO Auto-generated method stub
		try {
			// conn=DBConnect.getConnection();
			List<Sig_article> list = new ArrayList<Sig_article>();
			String sql = "select * from sig_article order by rand()  limit 14";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				if(!cover_url.equals("")){
				  list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));
				}
			}
			rs.close();
			ps.close();
			// conn.close();
			// DBConnect.session.disconnect();
			return list;

		} catch (SQLException e) {
			// DBConnect.session.disconnect();
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Sig_article> getRan3() {
		// TODO Auto-generated method stub
		try {
			// conn=DBConnect.getConnection();
			List<Sig_article> list = new ArrayList<Sig_article>();
			String sql = "select * from sig_article order by rand()  limit 3";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));

			}
			ps.close();
			rs.close();
			// conn.close();
			// DBConnect.session.disconnect();
			return list;

		} catch (SQLException e) {
			// DBConnect.session.disconnect();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void DeleteAllRow() {
		// TODO Auto-generated method stub
		try {
			 conn=DBConnect.getConnection();
			String sql = "TRUNCATE sig_article";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			 conn.close();
			 DBConnect.session.disconnect();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Sig_article> getRanFootBall1() {
		try {
			List<Sig_article> list = new ArrayList<Sig_article>();
			//String sql = "select * from sig_article where cid = ? order by rand()  limit 1 ";
			String sql = "select * from sig_article order by rand()  limit 1";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			//ps.setInt(1, 1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));

			}
			ps.close();
			rs.close();
			return list;

		} catch (SQLException e) {
			// DBConnect.session.disconnect();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Sig_article> getRanCritket1() {
		try {
			List<Sig_article> list = new ArrayList<Sig_article>();
			//String sql = "select * from sig_article where cid = ? order by rand()  limit 1 ";
			String sql = "select * from sig_article order by rand()  limit 1";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			//ps.setInt(1, 1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));

			}
			rs.close();
			ps.close();
			return list;

		} catch (SQLException e) {
			// DBConnect.session.disconnect();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Sig_article> getRanTennis() {
		// TODO Auto-generated method stub
		try {
			// conn=DBConnect.getConnection();
			List<Sig_article> list = new ArrayList<Sig_article>();
			//String sql = "select * from sig_article where cid = ? order by rand()  limit 1 ";
			String sql = "select * from sig_article order by rand()  limit 1";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			//ps.setInt(1, 1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));

			}
			ps.close();
			rs.close();
			// conn.close();
			// DBConnect.session.disconnect();
			return list;

		} catch (SQLException e) {
			// DBConnect.session.disconnect();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Sig_article> getRanRugy() {
		// TODO Auto-generated method stub
		try {
			// conn=DBConnect.getConnection();
			List<Sig_article> list = new ArrayList<Sig_article>();
			String sql = "select * from sig_article order by rand()  limit 1";
			//String sql = "select * from sig_article where cid = ? order by rand()  limit 1 ";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			//ps.setInt(1, 1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));

			}
			ps.close();
			rs.close();
			// conn.close();
			// DBConnect.session.disconnect();
			return list;

		} catch (SQLException e) {
			// DBConnect.session.disconnect();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Sig_article> getCyCling() {
		try {
			List<Sig_article> list = new ArrayList<Sig_article>();
			String sql = "select * from sig_article order by rand()  limit 2";
			//String sql = "select * from sig_article where cid = ? order by rand()  limit 2 ";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			//ps.setInt(1, 1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));

			}
			ps.close();
			rs.close();
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Sig_article> getRanSport() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try {
			// conn=DBConnect.getConnection();
			List<Sig_article> list = new ArrayList<Sig_article>();
			String sql = "select * from sig_article order by rand()  limit 20";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));

			}
			rs.close();
			ps.close();
			// conn.close();
			// DBConnect.session.disconnect();
			return list;

		} catch (SQLException e) {
			// DBConnect.session.disconnect();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Sig_article> getFootSport() {
		// TODO Auto-generated method stub
		try {
			// conn=DBConnect.getConnection();
			List<Sig_article> list = new ArrayList<Sig_article>();
			String sql = "select * from sig_article order by rand()  limit 16";
			//String sql = "select * from sig_article where cid = ? order by rand()  limit 16";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			//ps.setInt(1, 1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));

			}
			rs.close();
			ps.close();
			// conn.close();
			// DBConnect.session.disconnect();
			return list;

		} catch (SQLException e) {
			// DBConnect.session.disconnect();
			e.printStackTrace();
		}
		return null;
	}

	public void getCategory() {
		try {
			conn = DBConnect.getConnection();
			String sql = "select * from sig_category";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				System.out.println(Integer.toString(id));
				String name = rs.getString("name");
				System.out.println(name);
			}
			rs.close();
			ps.close();
			conn.close();
			DBConnect.session.disconnect();

		} catch (SQLException e) {
			// DBConnect.session.disconnect();
			e.printStackTrace();
		}

	}

	@Override
	public List<Sig_article> getMoreSport() {
		try {
			List<Sig_article> list = new ArrayList<Sig_article>();
			String sql = "select * from sig_article order by rand()  limit 10";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));

			}
			rs.close();
			ps.close();
			// conn.close();
			// DBConnect.session.disconnect();
			return list;

		} catch (SQLException e) {
			// DBConnect.session.disconnect();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Sig_article> getRan3Critket() {
		// TODO Auto-generated method stub
		try {
			List<Sig_article> list = new ArrayList<Sig_article>();
			//String sql = "select * from sig_article where cid = ? order by rand()  limit 4";
			String sql = "select * from sig_article order by rand()  limit 4";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			//ps.setInt(1, 1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));

			}
			ps.close();
			rs.close();
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Sig_article> getRan3FootBall() {
		try {
			List<Sig_article> list = new ArrayList<Sig_article>();
			String sql = "select * from sig_article order by rand()  limit 4";
			//String sql = "select * from sig_article where cid = ? order by rand()  limit 4 ";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			//ps.setInt(1, 1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));

			}
			rs.close();
			ps.close();
			return list;

		} catch (SQLException e) {
			// DBConnect.session.disconnect();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Sig_article> getRan3Rubgy() {
		// TODO Auto-generated method stub
		try {
			// conn=DBConnect.getConnection();
			List<Sig_article> list = new ArrayList<Sig_article>();
			String sql = "select * from sig_article order by rand()  limit 4";
			//String sql = "select * from sig_article where cid = ? order by rand()  limit 4 ";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			//ps.setInt(1, 1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));

			}
			rs.close();
			ps.close();
			// conn.close();
			// DBConnect.session.disconnect();
			return list;

		} catch (SQLException e) {
			// DBConnect.session.disconnect();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Sig_article> getRan3CyCling() {
		// TODO Auto-generated method stub
		try {
			// conn=DBConnect.getConnection();
			List<Sig_article> list = new ArrayList<Sig_article>();
			String sql = "select * from sig_article order by rand()  limit 4";
			//String sql = "select * from sig_article where cid = ? order by rand()  limit 4 ";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			//ps.setInt(1, 1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));

			}
			rs.close();
			ps.close();
			// conn.close();
			// DBConnect.session.disconnect();
			return list;

		} catch (SQLException e) {
			// DBConnect.session.disconnect();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Sig_article> getRubgySport() {
		// TODO Auto-generated method stub
		try {
			// conn=DBConnect.getConnection();
			List<Sig_article> list = new ArrayList<Sig_article>();
			String sql = "select * from sig_article order by rand()  limit 16";
			//String sql = "select * from sig_article where cid = ? order by rand()  limit 16";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			//ps.setInt(1, 6);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));

			}
			rs.close();
			ps.close();
			// conn.close();
			// DBConnect.session.disconnect();
			return list;

		} catch (SQLException e) {
			// DBConnect.session.disconnect();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Sig_article> getRan3Boxing() {
		// TODO Auto-generated method stub
		try {
			// conn=DBConnect.getConnection();
			List<Sig_article> list = new ArrayList<Sig_article>();
			String sql = "select * from sig_article order by rand()  limit 4";
			//String sql = "select * from sig_article where cid = ? order by rand()  limit 4 ";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			//ps.setInt(1, 1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));

			}
			rs.close();
			ps.close();
			// conn.close();
			// DBConnect.session.disconnect();
			return list;

		} catch (SQLException e) {
			// DBConnect.session.disconnect();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Sig_article> getTennisSport() {
		// TODO Auto-generated method stub
		try {
			// conn=DBConnect.getConnection();
			List<Sig_article> list = new ArrayList<Sig_article>();
			String sql = "select * from sig_article order by rand()  limit 16";
			//String sql = "select * from sig_article where cid = ? order by rand()  limit 16";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			//ps.setInt(1, 8);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));

			}
			// conn.close();
			rs.close();
			ps.close();
			// DBConnect.session.disconnect();
			return list;
		} catch (SQLException e) {
			// DBConnect.session.disconnect();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Sig_article> getGolfSport() {
		// TODO Auto-generated method stub
		try {
			// conn=DBConnect.getConnection();
			List<Sig_article> list = new ArrayList<Sig_article>();
			String sql = "select * from sig_article order by rand()  limit 16";
			//String sql = "select * from sig_article where cid = ? order by rand()  limit 16";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			//ps.setInt(1, 9);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));

			}
			rs.close();
			ps.close();
			// conn.close();
			// DBConnect.session.disconnect();
			return list;
		} catch (SQLException e) {
			// DBConnect.session.disconnect();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Sig_article> getMotoSport() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try {
			// conn=DBConnect.getConnection();
			List<Sig_article> list = new ArrayList<Sig_article>();
			String sql = "select * from sig_article order by rand()  limit 16";
			//String sql = "select * from sig_article where cid = ? order by rand()  limit 16";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			//ps.setInt(1, 10);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));

			}
			// conn.close();
			rs.close();
			ps.close();
			// DBConnect.session.disconnect();
			return list;
		} catch (SQLException e) {
			// DBConnect.session.disconnect();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Sig_article> getBoxingSport() {
		// TODO Auto-generated method stub
		try {
			// conn=DBConnect.getConnection();
			List<Sig_article> list = new ArrayList<Sig_article>();
			String sql = "select * from sig_article order by rand()  limit 16";
			//String sql = "select * from sig_article where cid = ? order by rand()  limit 16";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			//ps.setInt(1, 11);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));

			}
			rs.close();
			ps.close();
			// conn.close();
			// DBConnect.session.disconnect();
			return list;
		} catch (SQLException e) {
			// DBConnect.session.disconnect();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Sig_article> getSnookers() {
		// TODO Auto-generated method stub
		try {
			// conn=DBConnect.getConnection();
			List<Sig_article> list = new ArrayList<Sig_article>();
			String sql = "select * from sig_article order by rand()  limit 16";
			//String sql = "select * from sig_article where cid = ? order by rand()  limit 16";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			//ps.setInt(1, 12);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));

			}
			rs.close();
			ps.close();
			// conn.close();
			// DBConnect.session.disconnect();
			return list;
		} catch (SQLException e) {
			// DBConnect.session.disconnect();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Sig_article> getHorseRacing() {
		// TODO Auto-generated method stub
		try {
			// conn=DBConnect.getConnection();
			List<Sig_article> list = new ArrayList<Sig_article>();
			String sql = "select * from sig_article where cid = ? order by rand()  limit 16";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ps.setInt(1, 13);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));

			}
			rs.close();
			ps.close();
			// conn.close();
			// DBConnect.session.disconnect();
			return list;
		} catch (SQLException e) {
			// DBConnect.session.disconnect();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Sig_article> getCycling() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try {
			// conn=DBConnect.getConnection();
			List<Sig_article> list = new ArrayList<Sig_article>();
			String sql = "select * from sig_article where cid = ? order by rand()  limit 16 ";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ps.setInt(1, 14);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));

			}
			rs.close();
			ps.close();
			// conn.close();
			// DBConnect.session.disconnect();
			return list;

		} catch (SQLException e) {
			// DBConnect.session.disconnect();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Sig_article> getNews(int news_id) {
		// TODO Auto-generated method stub
		try {
			// conn=DBConnect.getConnection();
			List<Sig_article> list = new ArrayList<Sig_article>();
			String sql = "select * from sig_article where id = ? order by rand()  limit 10";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ps.setInt(1, news_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));

			}
			rs.close();
			ps.close();
			// conn.close();
			// DBConnect.session.disconnect();
			return list;
		} catch (SQLException e) {
			// DBConnect.session.disconnect();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Sig_article> getTin_lienwan(int category) {
		// TODO Auto-generated method stub
		try {
			// conn=DBConnect.getConnection();
			List<Sig_article> list = new ArrayList<Sig_article>();
			String sql = "select * from sig_article where cid = ? ";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ps.setInt(1, category);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));

			}
			rs.close();
			ps.close();
			// conn.close();
			// DBConnect.session.disconnect();
			return list;
		} catch (SQLException e) {
			// DBConnect.session.disconnect();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Sig_article> getCritket() {
		// TODO Auto-generated method stub
		try {
			List<Sig_article> list = new ArrayList<Sig_article>();
			String sql = "select * from sig_article where cid = ? order by rand()  limit 16 ";
			PreparedStatement ps = (PreparedStatement) conn
					.prepareStatement(sql);
			ps.setInt(1, 5);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String url = rs.getString("url");
				System.out.println(url);
				String title = rs.getString("title");
				String abstract_content = rs.getString("abstract");
				String text_content = rs.getString("text_content");
				String extracted_content = rs.getString("extracted_content");
				String html_content = rs.getString("html_content");
				String cover_url = rs.getString("cover_url");
				java.sql.Date published = rs.getDate("published");
				java.sql.Date crawled = rs.getDate("crawled");
				int wid = rs.getInt("wid");
				int cid = rs.getInt("cid");
				String meta = rs.getString("meta");
				list.add(new Sig_article(id, url, title, abstract_content,
						text_content, extracted_content, html_content,
						cover_url, published, crawled, wid, cid, meta));

			}
			rs.close();
			ps.close();
			return list;

		} catch (SQLException e) {
			// DBConnect.session.disconnect();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Sig_article> getSub(List<Sig_article> list, int start, int end) {
		// TODO Auto-generated method stub
		List<Sig_article> ds=new ArrayList<Sig_article>();
		for(int i=start;i<end;i++){
			ds.add(list.get(i));
		}
		
		return ds;
	}
}
