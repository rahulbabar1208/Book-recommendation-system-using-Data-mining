/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BRS_Servlet;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.text.DecimalFormat;
/**
 *
 * @author admin
 */
public class FindSimilarity 
{
    ArrayList user=new ArrayList();
    ArrayList item=new ArrayList();
    
    FindSimilarity()
    {
        
    }
    
    public void similarUser()
    {
        try
        {
            DBConnection db=new DBConnection();
            Statement st=db.stt;
            
            st.executeUpdate("delete from usersimilarity");
            
            
            ResultSet rs=st.executeQuery("select distinct UserName from purchase");
            
            while(rs.next())
            {
               user.add(rs.getString(1)); 
            }
            
                     
            
            
            for(int i=0;i<user.size();i++)
            {
                ArrayList lt1=new ArrayList();
                
                String u1=user.get(i).toString();
                ResultSet rt1=st.executeQuery("select distinct BID from purchase where UserName='"+u1+"'");
                while(rt1.next())
                {
                    lt1.add(rt1.getString(1));
                }
                
                
                ResultSet rst1=st.executeQuery("select UserRating from purchase where UserName='"+u1+"'");
                double k1=0;
                double d1=0;
                while(rst1.next())
                {
                    
                    double a4=Double.parseDouble(rst1.getString(1));
                    
                    d1=d1+a4;
                    k1++;
                }
                d1=d1/k1;
                    //System.out.println("avg1 "+u1+ " : "+d1);
                for(int j=i+1;j<user.size();j++)
                {
                    ArrayList lt2=new ArrayList();
                    
                    String u2=user.get(j).toString();
                    ResultSet rt2=st.executeQuery("select distinct BID from purchase where UserName='"+u2+"'");
                    while(rt2.next())
                    {
                        if(lt1.contains(rt2.getString(1)))
                        {
                            lt2.add(rt2.getString(1));
                        }
                    }
                
                    ResultSet rst2=st.executeQuery("select UserRating from purchase where UserName='"+u2+"'");
                    double k2=0;
                    double d2=0;
                    while(rst2.next())
                    {
                        
                        double a4=Double.parseDouble(rst2.getString(1));
                        
                        d2=d2+a4;
                        k2++;
                    }
                    d2=d2/k2;
                    //System.out.println("avg2 "+u2+ " : "+d2);
                    
                         
                   // System.out.println(u1+ " : "+u2 +" : "+lt2);
                    
                    double up1=0;
                    double dn1=0;
                    double dn2=0;
                    
                    for(int t=0;t<lt2.size();t++)
                    {
                        String sg1=lt2.get(t).toString();
                        ResultSet rst3=st.executeQuery("select UserRating from purchase where UserName='"+u1+"' and BID='"+sg1+"'");
                        
                        double r1=0;
                        double k3=0;
                        if(rst3.next())
                        {
                            
                            double a4=Double.parseDouble(rst3.getString(1));
                            r1=a4;
                            k3++;
                        }
                        r1=r1/k3;
                        
                        ResultSet rst4=st.executeQuery("select UserRating from purchase where UserName='"+u2+"' and BID='"+sg1+"'");
                        double r2=0;
                        double k4=0;
                        if(rst4.next())
                        {
                            
                            
                            double a4=Double.parseDouble(rst4.getString(1));
                            r2=a4;
                            k4++;
                        }
                        //r2=r2/k4;
                        
                        double ra=r1-d1;
                        double ru=r2-d2;
                        
                        up1=up1+(ra*ru);
                        dn1=dn1+(ra*ra);
                        dn2=dn2+(ru*ru);
                        
                        
                        
                    } //t
                    
                    double sim1=up1/((Math.sqrt(dn1))*Math.sqrt(dn2));
                    if(String.valueOf(sim1).equals("NaN"))
                    {
                        sim1=-1;
                    }
                    System.out.println(u1+" :  "+u2+"  :  "+sim1);
                    
                    DecimalFormat df=new DecimalFormat("#.###");
                    
                    String sim2=df.format(sim1);
                    
                    st.executeUpdate("insert into usersimilarity values('"+u1+"','"+u2+"','"+sim2+"')");
                    
                } //j
            } // i
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
            
    } // similar user
    
    
    
    public void similarItem()
    {
        try
        {
            DBConnection db=new DBConnection();
            Statement st=db.stt;
            
            st.executeUpdate("delete from itemsimilarity");
            
                           
            ResultSet rs2=st.executeQuery("select distinct BID from purchase");
            while(rs2.next())
            {
               item.add(rs2.getString(1)); 
            }
            
            
           // System.out.println("Item = "+item);
            
            for(int i=0;i<item.size();i++)
            {
                ArrayList lt1=new ArrayList();
                
                String sr1=item.get(i).toString();
                ResultSet rt1=st.executeQuery("select distinct UserName from purchase where BID='"+sr1+"'");
                while(rt1.next())
                {
                    lt1.add(rt1.getString(1));
                }
                
                
                ResultSet rst1=st.executeQuery("select * from purchase where BID='"+sr1+"'");
                double k1=0;
                double d1=0;
                while(rst1.next())
                {
                    
                    double a4=Double.parseDouble(rst1.getString(4));
                    
                    d1=d1+a4;
                    k1++;
                }
                d1=d1/k1;
                
               // System.out.println("lt1 "+lt1+ " : "+d1);
                
                for(int j=i+1;j<item.size();j++)
                {
                    ArrayList lt2=new ArrayList();
                    
                    String sr2=item.get(j).toString();
                    ResultSet rt2=st.executeQuery("select distinct UserName from purchase where BID='"+sr2+"'");
                    while(rt2.next())
                    {
                        if(lt1.contains(rt2.getString(1)))
                        {
                            lt2.add(rt2.getString(1));
                        }
                    }
                
                    ResultSet rst2=st.executeQuery("select * from purchase where BID='"+sr2+"'");
                    double k2=0;
                    double d2=0;
                    while(rst2.next())
                    {
                        
                        double a4=Double.parseDouble(rst2.getString(4));
                        
                        d2=d2+a4;
                        k2++;
                    }
                    d2=d2/k2;                   
                    
                       //  System.out.println("lt2 "+lt2+ " : "+d2);
                    
                    
                    double up1=0;
                    double dn1=0;
                    double dn2=0;
                    
                    for(int t=0;t<lt2.size();t++)
                    {
                        String sg1=lt2.get(t).toString();
                        ResultSet rst3=st.executeQuery("select * from purchase where BID='"+sr1+"' and UserName='"+sg1+"'");
                        
                        double r1=0;
                        double k3=0;
                        while(rst3.next())
                        {
                            
                            double a4=Double.parseDouble(rst3.getString(4));
                            r1=r1+a4;
                            k3++;
                        }
                        r1=r1/k3;
                        
                        ResultSet rst4=st.executeQuery("select * from purchase where BID='"+sr2+"' and UserName='"+sg1+"'");
                        double r2=0;
                        double k4=0;
                        while(rst4.next())
                        {
                            
                            
                            double a4=Double.parseDouble(rst4.getString(4));
                            r2=r2+a4;
                            k4++;
                        }
                        r2=r2/k4;
                        double ra=Math.abs(r1-d1);
                        double ru=Math.abs(r2-d2);
                        
                        up1=up1+(ra*ru);
                        dn1=dn1+(ra*ra);
                        dn2=dn2+(ru*ru);
                        
                       // System.out.println("=== "+r1+" : "+d1+" : "+r2+"  : "+d2);
                       // System.out.println("-- "+ra+" : "+ru);
                        
                    } //t
                    //System.out.println("== "+up1+" : "+dn1+" : "+dn2);
                    double sim1=up1/((Math.sqrt(dn1))*Math.sqrt(dn2));
                    
                    if(String.valueOf(sim1).equals("NaN"))
                    {
                        sim1=-1;
                    }
                    System.out.println(sr1+" :  "+sr2+"  :  "+sim1);
                    
                    
                    DecimalFormat df=new DecimalFormat("#.###");
                    
                    String sim2=df.format(sim1);
                    
                    st.executeUpdate("insert into itemsimilarity values('"+sr1+"','"+sr2+"','"+sim2+"')");
                    
                } //j
            } // i
            
            
            
            
            
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void userRating()
    {
        try
        {
            DBConnection db=new DBConnection();
            Statement st=db.stt;
            
            st.executeUpdate("delete from userrating");
            ArrayList item1=new ArrayList();
                           
            ResultSet rs2=st.executeQuery("select distinct BID from purchase");
            while(rs2.next())
            {
               item1.add(rs2.getString(1)); 
            }
            System.out.println(item1);
            for(int i=0;i<item1.size();i++)
            {
                String sr1=item1.get(i).toString();
                
                ResultSet rst1=st.executeQuery("select * from purchase where BID='"+sr1+"'");
                double k1=0;
                double d1=0;
                while(rst1.next())
                {
                    
                    double a4=Double.parseDouble(rst1.getString(4));
                    
                    d1=d1+a4;
                    k1++;
                }
                d1=d1/k1;
                DecimalFormat df=new DecimalFormat("#.###");
                
                        
                st.executeUpdate("insert into userrating values('"+sr1+"','"+df.format(d1)+"')");
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String ar[])
    {
        FindSimilarity fs=new FindSimilarity();
        fs.similarUser();
        fs.similarItem();
        fs.userRating();
    }
}
