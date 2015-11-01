package daoc;
import java.util.List;

import newsport.Sig_article;
public interface Sig_articleDOA {
   public void addSig(Sig_article p);
   public List<Sig_article> getList();
   public List<Sig_article> getRan();
   public List<Sig_article> getRan3();
   public void DeleteAllRow();
   public List<Sig_article> getRanFootBall1();
   public List<Sig_article> getRanCritket1();
   public List<Sig_article> getCritket();
   public List<Sig_article> getRanTennis();
   public List<Sig_article> getRanRugy();
   public List<Sig_article> getCyCling();
   public List<Sig_article> getRanSport();
   public List<Sig_article> getFootSport();
   public List<Sig_article> getMoreSport();
   
   public List<Sig_article> getRan3Critket();
   public List<Sig_article> getRan3FootBall();
   public List<Sig_article> getRan3Rubgy();
   public List<Sig_article> getRan3CyCling();
   public List<Sig_article> getRan3Boxing();
   
   public List<Sig_article> getRubgySport();
   public List<Sig_article> getTennisSport();
   public List<Sig_article> getGolfSport();
   public List<Sig_article> getMotoSport();
   public List<Sig_article> getBoxingSport();
   
   public List<Sig_article> getSnookers();
   public List<Sig_article> getHorseRacing();
   public List<Sig_article> getCycling();
   
   public List<Sig_article> getNews(int news_id);
   public List<Sig_article> getTin_lienwan(int category);
   public void addCategory();
   public void RemoveCategory();
   public List<Sig_article> getSub(List<Sig_article> list,int start,int end);
   
   public List<Sig_article> getRanRelatedNews();
   public List<Sig_article> removeDuplicateSig(List<Sig_article> arrList);
   public List<String> removeDuplicate(List<String> arrList);
}
