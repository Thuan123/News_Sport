<%@page import="daoc.connectionpool.DBPool"%>
<%@page import="daoc.DBConnect"%>
<%@page import="daoc.Sig_articleDOAImpl"%>
<%@page import="newsport.Sig_article"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>News Sport</title>
<link rel="stylesheet" href="layout/styles/layout.css" type="text/css" />
</head>
<body id="top">
	<%
		Sig_articleDOAImpl sig = new Sig_articleDOAImpl();
		List<Sig_article> list = new ArrayList<Sig_article>();
		List<Sig_article> listmore = new ArrayList<Sig_article>();
		List<Sig_article> listCriket = new ArrayList<Sig_article>();
		List<Sig_article> listTenis = new ArrayList<Sig_article>();

		List<Sig_article> listSportMore = new ArrayList<Sig_article>();
        List<Sig_article> list_lienwan=new ArrayList<Sig_article>();
		

		String news_name = null;

		if (request.getParameter("news_name") != null) {
			news_name = request.getParameter("news_name");
		}
		if(news_name!=null){
			list=sig.getRanRelatedNews();
			listSportMore = sig.getMoreSport();
			listmore = sig.getRan3Boxing();
			listCriket = sig.getRanCritket1();
			listTenis = sig.getRanFootBall1();
			for(int i=0;i<list.size();i++){
				if(list.get(i).getMeta()!=null){
				   if(list.get(i).getMeta().indexOf(news_name)!=-1){
					   list_lienwan.add(list.get(i));
					  System.out.println("Thuan");
				   }
				}
				
			}
			System.out.println(news_name);
		}
        DBPool.putConnection(sig.conn);
		//sig.conn.close();
		//DBConnect.session.disconnect();
	%>
	<div class="wrapper col0">
		<div id="topline">
			<ul>
				<!--<li><a href="#">Libero</a></li>
                <li><a href="#">Maecenas</a></li>-->
				<!--<li><a href="#">Sign in</a></li>
				<li class="last"><a href="#">Sign up</a></li>-->
			</ul>
			<br class="clear" />
		</div>
	</div>
	<!-- ####################################################################################################### -->
	<div class="wrapper">
		<div id="header">
			<div class="fl_left">
				<h1>
					<a href="index.jsp"><strong>N</strong>ews <strong>S</strong>port</a>
				</h1>
				<p>All News Sport</p>
			</div>
			<div class="fl_right">
				<!--<a href="#"><img src="images/demo/468x60.gif" alt="" /></a>-->
			</div>
			<br class="clear" />
		</div>
	</div>
	<!-- ####################################################################################################### -->
	<div class="wrapper col2">
		<div id="topbar">
			<jsp:include page="topnav.jsp"></jsp:include>
			
			<br class="clear" />
		</div>
	</div>
	<!-- ####################################################################################################### -->
	<div class="wrapper">
		
	</div>
	<!-- ####################################################################################################### -->
	<div class="wrapper">
		<div class="container">
			<div class="content">
				<div id="comments">
					<ul class="commentlist">
					  <h2>RELATED NEWS</h2>
					  <br>
					  <%
							for (Sig_article p : list_lienwan) {
						%>
						<%
							if (!p.getTitle().equals("")) {
						%>
						<div>
							<a href="details.jsp?news_id=<%=p.getId()%>"><img
								src=<%=p.getCover_url()%> alt="" width="100px" height="75px" /></a>
						</div>
						<br>
						<div>
							<a href="details.jsp?news_id=<%=p.getId()%>"><%=p.getTitle()%></a>
						</div>
						<div>
							<p><%=p.getAbstract_content()%></p>
						</div>
						<br>
						<br>
						<%
							}
						%>
						<%
							}
						%>
					</ul>
				</div>
			</div>
			<div class="column">
				<div class="content">
					<h2>MORE SPORT</h2>
					<ul>
						<%
							//listSportMore = sig.getMoreSport();
							for (Sig_article p : listSportMore) {
						%>
						<%
							if (!p.getTitle().equals("")) {
						%>
						<li><a href="details.jsp?news_id=<%=p.getId()%>"><%=p.getTitle()%></a></li>
						<%
							}
						%>
						<%
							}
						%>
					</ul>
				</div>
				<div class="holder">
					<%
						for (Sig_article p : listCriket) {
					%>
					<h4 class="title">
						<img src=<%=p.getCover_url()%> alt="" width="70px" height="70px" /><a
							href="details.jsp?news_id=<%=p.getId()%>"><%=p.getTitle()%></a>
					</h4>
					<p><%=p.getAbstract_content()%></p>
					<p class="readmore">
						<a href="details.jsp?news_id=<%=p.getId()%>">Continue Reading
							&raquo;</a>
					</p>
				</div>
				<%
					}
				%>
				<div id="featured">
					<ul>
						<li>
							<h2>FOOTBALL</h2> <%
 	for (Sig_article p : listTenis) {
 %>
							<p class="imgholder">
								<img src=<%=p.getCover_url()%> alt="" width="240px"
									height="100px" />
							</p>
							<h3 class="title">
								<a href="details.jsp?news_id=<%=p.getId()%>"><%=p.getTitle()%></a>
							</h3>
							<p><%=p.getAbstract_content()%></p>
							<p class="readmore">
								<a href="details.jsp?news_id=<%=p.getId()%>">Continue
									Reading &raquo;</a>
							</p> <%
 	}
 %>
						</li>
					</ul>
				</div>
			</div>
			<br class="clear" />
		</div>
	</div>
	<!-- ####################################################################################################### -->
	<div class="wrapper">
		<div id="socialise">
			<ul>
				<li><a href="#"><img src="layout/images/facebook.gif"
						alt="" /><span>Facebook</span></a></li>
				<li><a href="#"><img src="layout/images/rss.gif" alt="" /><span>FeedBurner</span></a></li>
				<li class="last"><a href="#"><img
						src="layout/images/twitter.gif" alt="" /><span>Twitter</span></a></li>
			</ul>
			<div id="newsletter">
				<h2>NewsLetter Sign Up !</h2>
				<p>Please enter your Email and Name to join.</p>
				<form action="#" method="post">
					<fieldset>
						<legend>Digital Newsletter</legend>
						<div class="fl_left">
							<input type="text" value="Enter name here&hellip;"
								onfocus="this.value=(this.value=='Enter name here&hellip;')? '' : this.value ;" />
							<input type="text" value="Enter email address&hellip;"
								onfocus="this.value=(this.value=='Enter email address&hellip;')? '' : this.value ;" />
						</div>
						<div class="fl_right">
							<input type="submit" name="newsletter_go" id="newsletter_go"
								value="&raquo;" />
						</div>
					</fieldset>
				</form>
				<p>
					To unsubsribe please <a href="#">click here &raquo;</a>.
				</p>
			</div>
			<br class="clear" />
		</div>
	</div>
	<!-- ####################################################################################################### -->
	<div class="wrapper col8">
		<div id="copyright">
			<p class="fl_left">
				Copyright &copy; 2014 - All Rights Reserved - <a href="#">Domain
					Name</a>
			</p>
			<br class="clear" />
		</div>
	</div>
</body>
</html>