/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BRS_Servlet;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.text.DecimalFormat;

/**
 *
 * @author admin
 */
public class ARM 
{
    ArrayList item=new ArrayList();
    
    List<int[]> itemsets ;
    String transaFile;
    int numItems;
    int numTransactions;
    double minSup=0.2;
    String out2="";
    String path;
    DecimalFormat df=new DecimalFormat("#.###");
    
    ARM(String pp)
    {
        path=pp;
        try
        {
            DBConnection db=new DBConnection();
            Statement st=db.stt;
            st.executeUpdate("delete from arm");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public String getTrans()
    {
        String trans="";
        try
        {
            DBConnection db=new DBConnection();
            Statement st=db.stt;
            ResultSet rs=st.executeQuery("select distinct BID from purchase");
            while(rs.next())
            {
                item.add(rs.getString(1));
            }
            
            ResultSet rs2=st.executeQuery("select distinct username from purchase");
            ArrayList user=new ArrayList();
            while(rs2.next())
            {
                user.add(rs2.getString(1));
            }
            
            ResultSet rs3=st.executeQuery("select distinct Date from purchase");
            ArrayList dte=new ArrayList();
            while(rs3.next())
            {
                dte.add(rs3.getString(1));
            }
            
            
            for(int i=0;i<user.size();i++)
            {
                String u1=user.get(i).toString();
               
                for(int j=0;j<dte.size();j++)
                {
                    String d1=dte.get(j).toString();
                    ResultSet rs4=st.executeQuery("select distinct BID from purchase where UserName='"+u1+"' and Date='"+d1+"'");
                    while(rs4.next())
                    {
                        String g1=rs4.getString(1);
                        int ind=item.indexOf(g1)+1;
                        trans=trans+ind+" ";
                    }
                    trans=trans+"\n";        
                }
                
            }
            System.out.println("Trans "+trans);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return trans;
    }
    public void findrule(String fn1)
    {
        try
        {
            transaFile=fn1;
            numItems = 0;
            numTransactions=0;
            BufferedReader data_in = new BufferedReader(new FileReader(transaFile));
            while (data_in.ready()) 
            {              
                String line=data_in.readLine();
                if (line.matches("\\s*")) 
                    continue;
                numTransactions++;
                StringTokenizer t = new StringTokenizer(line," ");
                while (t.hasMoreTokens()) 
                {
                    int x = Integer.parseInt(t.nextToken());
                    //log(x);
                    if (x+1>numItems) 
			numItems=x+1;
                }              
            }  
            
            createItemsetsOfSize1(); 
			
            int itemsetNumber=1; 
            int nbFrequentSets=0;
       
            while (itemsets.size()>0)
            {

                calculateFrequentItemsets();

                if(itemsets.size()!=0)
                {
                    nbFrequentSets+=itemsets.size();
                
                    createNewItemsetsFromPreviousOnes();
                }
                
                itemsetNumber++;
            }


		
		File fe2=new File(path+"out2.txt");
		FileOutputStream fos2=new FileOutputStream(fe2);
		fos2.write(out2.getBytes());
		fos2.close();
		
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void createItemsetsOfSize1() 
    {
        itemsets = new ArrayList<int[]>();
        for(int i=0; i<numItems; i++)
        {
            int[] cand = {i};
            itemsets.add(cand);
        }
	System.out.println("-------- "+itemsets.size()+" : "+itemsets.get(0).length);	
		
    }
    
    void calculateFrequentItemsets() throws Exception
    {
              
        List<int[]> frequentCandidates = new ArrayList<int[]>(); 

        boolean match; 
        int count[] = new int[itemsets.size()];
        
        BufferedReader data_in = new BufferedReader(new InputStreamReader(new FileInputStream(transaFile)));

        boolean[] trans = new boolean[numItems];
               
        for (int i = 0; i < numTransactions; i++) 
        {
            String line = data_in.readLine();
            line2booleanArray(line, trans);

        
            for (int c = 0; c < itemsets.size(); c++) 
            {
                match = true; 
                int[] cand = itemsets.get(c);
              
                for (int xx : cand) 
                {
                    if (trans[xx] == false) 
                    {
                        match = false;
                        break;
                    }
                }
                if (match) 
                { 
                    count[c]++;
              
                }
            }

        }
              
        data_in.close();
        for (int i = 0; i < itemsets.size(); i++) 
        {
                        
            if ((count[i] / (double) (numTransactions)) >= minSup) 
            {
                foundFrequentItemSet(itemsets.get(i),count[i]);
                frequentCandidates.add(itemsets.get(i));
            }
                       
        }

        
        itemsets = frequentCandidates;
    }
    
    void createNewItemsetsFromPreviousOnes()
    {
        
        int currentSizeOfItemsets = itemsets.get(0).length;
               
        HashMap<String, int[]> tempCandidates = new HashMap<String, int[]>(); 
       
        
        for(int i=0; i<itemsets.size(); i++)
        {
            for(int j=i+1; j<itemsets.size(); j++)
            {
                int[] X = itemsets.get(i);
                int[] Y = itemsets.get(j);

                assert (X.length==Y.length);
               
                
                int [] newCand = new int[currentSizeOfItemsets+1];
                for(int s=0; s<newCand.length-1; s++) 
                {
                        newCand[s] = X[s];
                }
                   
                int ndifferent = 0;
                
                for(int s1=0; s1<Y.length; s1++)
                {
                        boolean found = false;
                        
                    for(int s2=0; s2<X.length; s2++) 
                    {
                        if (X[s2]==Y[s1]) 
                        {
                                found = true;
                                break;
                        }
                        }
                        if (!found)
                        { 
                            ndifferent++;
                                
                            newCand[newCand.length -1] = Y[s1];
                        }
               
                }
               
                
                assert(ndifferent>0);
               
               
                if (ndifferent==1) 
                {
                    Arrays.sort(newCand);
                    tempCandidates.put(Arrays.toString(newCand),newCand);
                }
            }
        }       
        
        itemsets = new ArrayList<int[]>(tempCandidates.values());     

    }


   
    private void line2booleanArray(String line, boolean[] trans) 
    {
        Arrays.fill(trans, false);
        StringTokenizer stFile = new StringTokenizer(line, " ");
    
        while (stFile.hasMoreTokens())
        {
               
            int parsedVal = Integer.parseInt(stFile.nextToken());
            trans[parsedVal]=true; //if it is not a 0, assign the value to true
        }
    }
    void foundFrequentItemSet(int[] itemset, int support) 
    {
        try
        {
            System.out.println(Arrays.toString(itemset) + "  ("+ ((support / (double) numTransactions))+" "+support+")");
            //out1=out1+Arrays.toString(itemset) + "  ("+ ((support / (double) numTransactions))+" "+support+")\n";
            out2=out2+Arrays.toString(itemset)+"\t"+df.format(support / (double) numTransactions)+"\n";
        
            DBConnection db=new DBConnection();
            Statement st=db.stt;
            String r1=Arrays.toString(itemset).replace("[", "");
            r1=r1.replace("]", "");
            r1=r1.replace(",", "#");
            r1=r1.trim();
            
            System.out.println("=== "+r1);
            String bids="";
            if(r1.contains("#"))
            {
                String r2[]=r1.split("#");
                for(int i=0;i<r2.length;i++)
                {
                    bids=bids+item.get(Integer.parseInt(r2[i].trim())-1).toString()+" , ";
                }
                bids=bids.substring(0, bids.lastIndexOf(","));
            }
            else
            {
                bids=item.get(Integer.parseInt(r1.trim())-1).toString();
            }
        
            double sup=support / (double) numTransactions;
            String sg=df.format(sup);
            st.executeUpdate("insert into ARM values('"+bids+"','"+sg+"')");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
		
    }
    
    public static void main(String arg[])
    {
        ARM ar=new ARM("");
        ar.getTrans();
    }
}
