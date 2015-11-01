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
<title>New Sport</title>

<link rel="stylesheet" href="layout/styles/layout.css" type="text/css" />
</head>
<body id="top">
	<%
		List<Sig_article> list = new ArrayList<Sig_article>();
		List<Sig_article> listmore = new ArrayList<Sig_article>();
		List<Sig_article> listCriket = new ArrayList<Sig_article>();
		List<Sig_article> listTenis = new ArrayList<Sig_article>();

		List<Sig_article> list_lienwan = new ArrayList<Sig_article>();
		List<Sig_article> list_related = new ArrayList<Sig_article>();
		List<String> list_meta = new ArrayList<String>();
		String id_news = null;

		if (request.getParameter("news_id") != null) {
			id_news = request.getParameter("news_id");
		}
		Sig_articleDOAImpl sig = new Sig_articleDOAImpl();
		if (id_news != null) {
			list = sig.getNews(Integer.parseInt(id_news));
			listmore = sig.getMoreSport();
			listCriket = sig.getRanCritket1();
			listTenis = sig.getRanTennis();
			if(list.size()!=0){
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getMeta() != null) {
					String result[] = list.get(i).getMeta().split("[ ]");
					for (int j = 0; j < result.length; j++) {
						if ((result[j] != null)
								&& (!result[j].toString().endsWith(
										"#about>")
										&& (!result[j].toString().endsWith(
												"#contain>"))
										&& (result[j].toString().indexOf(
												"document") == -1)
										&& (result[j].toString().indexOf(
												"statement") == -1)
										&& (!result[j].toString().endsWith(
												"#subject>"))
										&& (!result[j].toString().endsWith(
												"#object>"))
										&& (!result[j].toString().endsWith(
												"#said>"))
										&& (!result[j].toString().endsWith(
												"#saidThat>"))
										&& (!result[j].toString().endsWith(
												"#string>"))
										&& (!result[j].toString().endsWith(
												"#type>"))
										&& (!result[j].toString().endsWith(
												"#hasURL>"))
										&& (!result[j].toString().endsWith(
												"#News>"))
										&& (!result[j].toString().endsWith(
												"#predicate>")) && (!result[j]
										.toString().endsWith("#Statement>")))) {
							if ((result[j].toString().indexOf('#') != -1)
									&& (result[j].toString().indexOf('>') != -1)) {
								System.out.println(result[j].toString()
										.substring(
												result[j].toString()
														.indexOf('#') + 1,
												result[j].toString()
														.indexOf('>')));

								list_meta.add(result[j].toString()
										.substring(
												result[j].toString()
														.indexOf('#') + 1,
												result[j].toString()
														.indexOf('>')));
							}
						}
					}
				}
			}
			if (!list_meta.isEmpty()) {
				list_meta = sig.removeDuplicate(list_meta);

				list_related = sig.getRanRelatedNews();
				for (int i = 0; i < list_related.size(); i++) {
					for (int j = 0; j < list_meta.size(); j++) {

						if ((list_related.get(i).getMeta() != null)) {
							if (list_related.get(i).getMeta().toString()
									.indexOf(list_meta.get(j).toString()) != -1) {
								list_lienwan.add(list_related.get(i));
								break;
							}
						}
					}
				}

				list_lienwan = sig.removeDuplicateSig(list_lienwan);

			}
		}
		}
		DBPool.putConnection(sig.conn);
		//sig.conn.close();
		//DBConnect.session.disconnect();
	%>

	<div class="wrapper col0">
		<div id="topline">
			<ul>
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
	<!-- ####################################################################################################### -->
	<div class="wrapper">
		<div class="container">
			<div class="content">
				<div id="comments">
					<%
						for (Sig_article p : list) {
					%>
					<div>
						<%
							if (p.getCover_url() != "") {
						%>
						<img class="avatar" src=<%=p.getCover_url()%> width="420"
							height="300" alt="" /> <br> <br> <br> <span
							class="name"><h2>
								<a><%=p.getTitle()%></a>
							</h2></span>
						<%
							}
						%>
						<%
							if (p.getCover_url() == "") {
						%>
						<h2>
							<a><%=p.getTitle()%></a>
						</h2>
						<%
							}
						%>
						<div>
							<h2>
								<font size="5"><%=p.getAbstract_content()%></font>
								<!--p.getMeta()-->
							</h2>
							<br> <br>

						</div>
						<div>
							<h4>
								<font size="3"><%=p.getText_content()%></font>
							</h4>

						</div>
						<br> <br>
					</div>
					<%
						}
					%>
					<br class="clear" />
				</div>
				<div>
					<h2>
						<font size="4">Related News</font>

					</h2>
					<br> <br>
					<%
						for (Sig_article p : list_lienwan) {
					%>
					<%
						if (!p.getTitle().equals("")) {
					%>
					<div>
						<img class="avatar" src=<%=p.getCover_url()%> width="120"
							height="100" alt="" /> <br> <br> <br> <span
							class="name"><h4>
								<a href="details.jsp?news_id=<%=p.getId()%>"><%=p.getTitle()%></a>

							</h4></span>
						<div>
							<h2>
								<font size="2"><%=p.getAbstract_content()%></font>
							</h2>
							<br> <br>

						</div>
					</div>
					<%
						}
					%>
					<%
						}
					%>
				</div>
			</div>

			<div class="column">
				<div class="content">
					<h2>MORE SPORT</h2>
					<ul class="commentlist">
						<%
							for (Sig_article p : listmore) {
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
						<%
							if (p.getCover_url() != "") {
						%>
						<img src=<%=p.getCover_url()%> alt="" width="70px" height="70px" /><a
							href="details.jsp?news_id=<%=p.getId()%>"><%=p.getTitle()%></a>
						<%
							}
						%>
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
 %> <%
 	if (p.getCover_url() != "") {
 %>
							<p class="imgholder">
								<img src=<%=p.getCover_url()%> alt="" width="240px"
									height="100px" />
							</p> <%
 	}
 %>
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
				<div class="content">
					<h2>META</h2>


					<ul class="commentlist">
						<%
							for (Sig_article p : list) {
						%>
						<%
							if (p.getMeta() == null) {
						%>						
						<%
							}
						%>
						<%
							if (p.getMeta() != null) {
								System.out.println(p.getMeta().toString());
						%>
					
						<%
							for (int i = 0; i < list_meta.size(); i++) {
						%>
						<li><a
							href="relatednews.jsp?news_name=<%=list_meta.get(i).toString()%>"><%=list_meta.get(i).toString()%></a></li>
						<%
							}
						%>

						<%
							}
						%>
						<%
							}
						%>
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
				Copyright &copy; 2015 - All Rights Reserved - <a href="#">Domain
					Name</a>
			</p>
			<br class="clear" />
		</div>
	</div>
</body>
</html>