<%@page import="daoc.connectionpool.DBPool"%>
<%@page import="daoc.Sig_articleDOAImpl"%>
<%@page import="newsport.Sig_article"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List" %>
<%@page import="daoc.DBConnect"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>News Sport </title>
<link rel="stylesheet" href="layout/styles/layout.css" type="text/css" />
<script type="text/javascript" src="layout/scripts/jquery.min.js"></script>
<!-- Homepage Specific -->
<script type="text/javascript" src="layout/scripts/galleryviewthemes/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="layout/scripts/galleryviewthemes/jquery.timers.1.2.js"></script>
<script type="text/javascript" src="layout/scripts/galleryviewthemes/jquery.galleryview.2.1.1.min.js"></script>
<script type="text/javascript" src="layout/scripts/galleryviewthemes/jquery.galleryview.setup.js"></script>
</head>
<body id="top">

	<%
      Sig_articleDOAImpl sig=new Sig_articleDOAImpl();
      List<Sig_article> list=new ArrayList<Sig_article>();
      List<Sig_article> list3=new ArrayList<Sig_article>();
      List<Sig_article> list4=new ArrayList<Sig_article>();
      List<Sig_article> list5=new ArrayList<Sig_article>();
      List<Sig_article> list6=new ArrayList<Sig_article>();
      List<Sig_article> list7=new ArrayList<Sig_article>();
      List<Sig_article> list8=new ArrayList<Sig_article>();
      List<Sig_article> list9=new ArrayList<Sig_article>();
      List<Sig_article> list10=new ArrayList<Sig_article>();
   %>
  <div class="wrapper col0">
  <div id="topline">
    <ul>
     <!-- <li><a href="#">Sign in</a></li>
          <li class="last"><a href="#">Sign up</a></li>-->
    </ul>
    <br class="clear" />
  </div>
</div>
<!-- ####################################################################################################### -->
<div class="wrapper">
  <div id="header">
    <div class="fl_left">
      <h1><a href="index.jsp"><strong>N</strong>ews <strong>S</strong>port</a></h1>
      <p>All News Sport of the world</p>
    </div>
    <!--<div class="fl_right"><!--<a href="#"><img src="images/demo/468x60.gif" alt="" /></a>--></div>
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
  <div class="container">
    <div class="content">
      <div id="featured_slide">
        <ul id="featurednews">
        <%
           list=sig.getRan();
		   for(Sig_article p : list){ 
		 %>
         <%
           if((!p.getTitle().equals(""))&&(!p.getCover_url().equals(""))){
         %>		 
          <li><img src=<%=p.getCover_url() %> alt="" />
            <div class="panel-overlay">
              <h3><%=p.getTitle() %></h3>
                <a href="details.jsp?news_id=<%=p.getId() %>">Continue Reading &raquo;</a></p>
            </div>
          </li>
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
      <ul class="latestnews">
         <%
         list3=sig.getRan3();
		   for(Sig_article p : list3){ 
		 %>
		 <%
		 if(!p.getTitle().equals("")){
		 %>
        <li><img src=<%=p.getCover_url() %> alt="" width="100px" height="100px"/></br>
          <p><strong><a href="details.jsp?news_id=<%=p.getId() %>"><%=p.getTitle() %></a></br></strong><%=p.getAbstract_content() %></p>
        </li>
        <%
		 }
        %>
         <%
		   }
		 %>
      </ul>
    </div>
    <br class="clear" />
  </div>
</div>
<!-- ####################################################################################################### -->
<div class="wrapper">
  <div id="adblock">
    <div class="fl_left"><!--<a href="#"><img src="images/demo/468x60.gif" alt="" /></a>--></div>
    <div class="fl_right"><!--<a href="#"><img src="images/demo/468x60.gif" alt="" /></a>--></div>
    <br class="clear" />
  </div>
  <div id="hpage_cats">
    <div class="fl_left">
     
      <%
      list4=sig.getRanFootBall1();
      for(Sig_article p : list4){ 
      %>
      <img src=<%=p.getCover_url() %> alt="" width="100px" height="100px"/>
      <p><strong><a href="details.jsp?news_id=<%=p.getId() %>"><%=p.getTitle() %></a></strong></p>
      <p><%=p.getAbstract_content() %></p>
      <%
		}
	  %>
    </div>
    <div class="fl_right">
      <!--<h2><a href="#">CRICKET &raquo;</a></h2>-->
      <%
      list5=sig.getRanCritket1();
      for(Sig_article p : list5){ 
      %>
      <img src=<%=p.getCover_url() %> alt="" width="100px" height="100px"/>
      <p><strong><a href="details.jsp?news_id=<%=p.getId() %>"><%=p.getTitle() %></a></strong></p>
      <p><%=p.getAbstract_content() %></p>
      <%
		}
	  %>
    </div>
    <br class="clear" />
    <div class="fl_left">
    
      <!--<h2><a href="#">TENNIS &raquo;</a></h2>-->
      <%
      list6=sig.getRanTennis();
      for(Sig_article p : list6){ 
      %>
      <img src=<%=p.getCover_url() %> alt="" width="100px" height="100px"/>
      <p><strong><a href="details.jsp?news_id=<%=p.getId() %>"><%=p.getTitle() %></a></strong></p>
      <p><%=p.getAbstract_content() %></p>
      <%
		}
	  %>
    </div>
    <div class="fl_right">
      <!--<h2><a href="#">RUGBY &raquo;</a></h2>-->
       <%
       list7=sig.getRanRugy();
        for(Sig_article p : list7){ 
       %>
      <img src=<%=p.getCover_url() %> alt="" width="100px" height="100px"/>
      <p><strong><a href="details.jsp?news_id=<%=p.getId() %>"><%=p.getTitle() %></a></strong></p>
      <p><%=p.getAbstract_content() %></p>
      <%
		}
	  %>
    </div>
    <br class="clear" />
  </div>
</div>
<!-- ####################################################################################################### -->
<div class="wrapper">
  <div class="container">
    <div class="content">
      <div id="hpage_latest">
        <h2><a href="#">CYCLING &raquo;</a></h2>
        <ul>
          <%
          list8=sig.getCyCling();
     
            for(Sig_article p : list8){ 
          %>
          <li><img src=<%=p.getCover_url() %> alt="" width="190px" height="130px"/>
            <p><%=p.getTitle() %></p>
            <p class="readmore"><a href="details.jsp?news_id=<%=p.getId() %>">Continue Reading &raquo;</a></p>
          </li>
          <%
            }
          %>
        </ul>
        <br class="clear" />
      </div>
    </div>
    <div class="column">
      <%
      list6=sig.getRanTennis();
      for(Sig_article p : list6){ 
      %>
      <div class="holder"><a href="details.jsp?news_id=<%=p.getId() %>"><img src=<%=p.getCover_url() %> alt="" width="300px" height="250px"/></a></div>
      <% 
      }
      %>
    </div>
    <br class="clear" />
  </div>
</div>
<!-- ####################################################################################################### -->
<div class="wrapper">
<div id="adblock">
     <%
  
     list9=sig.getRanSport();
     DBPool.putConnection(sig.conn);
     //sig.conn.close();
     
       for(Sig_article p : list9){ 
     %>
     <%
       if(!p.getTitle().equals("")){
     %>
      <div><a href="details.jsp?news_id=<%=p.getId() %>"><img src=<%=p.getCover_url() %> alt="" width="100px" height="75px"/></a></div><br>
      <div><a href="details.jsp?news_id=<%=p.getId() %>"><%=p.getTitle() %></a></div>
      <div><p><%=p.getAbstract_content() %></p></div>
      <%
       }
      %>
     <%
       }
     %>
    <br class="clear" />
</div>

</div>
<!-- ####################################################################################################### -->
 <div class="wrapper">
  <div id="socialise">
    <ul>
      <li><a href="#"><img src="layout/images/facebook.gif" alt="" /><span>Facebook</span></a></li>
      <li><a href="#"><img src="layout/images/rss.gif" alt="" /><span>FeedBurner</span></a></li>
      <li class="last"><a href="#"><img src="layout/images/twitter.gif" alt="" /><span>Twitter</span></a></li>
    </ul>
    <div id="newsletter">
      <h2>NewsLetter Sign Up !</h2>
      <p>Please enter your Email and Name to join.</p>
      <form action="#" method="post">
        <fieldset>
          <legend>Digital Newsletter</legend>
          <div class="fl_left">
            <input type="text" value="Enter name here&hellip;"  onfocus="this.value=(this.value=='Enter name here&hellip;')? '' : this.value ;" />
            <input type="text" value="Enter email address&hellip;"  onfocus="this.value=(this.value=='Enter email address&hellip;')? '' : this.value ;" />
          </div>
          <div class="fl_right">
            <input type="submit" name="newsletter_go" id="newsletter_go" value="&raquo;" />
          </div>
        </fieldset>
      </form>
      <p>To unsubsribe please <a href="#">click here &raquo;</a>.</p>
    </div>
    <br class="clear" />
  </div>
</div>
<!-- ####################################################################################################### -->
<div class="wrapper col8">
  <div id="copyright">
    <p class="fl_left">Copyright &copy; 2015 - All Rights Reserved - <a href="#">Domain Name</a></p>
    <br class="clear" />
  </div>
</div>
</body>
</html>