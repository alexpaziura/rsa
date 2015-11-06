package cryptology.rsa;

import java.math.BigInteger;
import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	Button btnCheck1,btnCheck2,btnCalc1,btnCalc2,btnDecrypt,btnEncrypt;
	TextView tN,tFi,tD,tZakr,tVidkr;
	EditText etP,etVidkr,etQ,etE1,etE2,etN,etZakr;
	int p,q,n1,Fi,e1,e2,d,n2;
	String vidkr1,zakr_text1,shifrotext,open_text;
	CheckBox ch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       
        TabHost tabs = (TabHost) findViewById(android.R.id.tabhost);
		tabs.setup();
		TabHost.TabSpec spec = tabs.newTabSpec("tag1");
		spec.setContent(R.id.tab1);
		spec.setIndicator("Підготовка");
		tabs.addTab(spec);
		btnCheck1 = (Button) findViewById(R.id.check1);
		btnCheck1.setOnClickListener(this);
		btnCalc1 = (Button) findViewById(R.id.calc1);
		btnCalc1.setOnClickListener(this);
		btnCalc1.setVisibility(View.GONE);
        etP = (EditText) findViewById(R.id.etP);
        etQ = (EditText) findViewById(R.id.etQ);
        tN = (TextView) findViewById(R.id.tN);
        tN.setVisibility(View.GONE);
        tFi = (TextView) findViewById(R.id.tFi);
        tFi.setVisibility(View.GONE);
        etE1 = (EditText) findViewById(R.id.etE1);
        etE1.setVisibility(View.GONE);
        btnCheck2 = (Button) findViewById(R.id.check2);
		btnCheck2.setOnClickListener(this);
		btnCheck2.setVisibility(View.GONE);
		btnCalc2 = (Button) findViewById(R.id.calc2);
		btnCalc2.setOnClickListener(this);
		btnCalc2.setVisibility(View.GONE);
		tD = (TextView) findViewById(R.id.tD);
		tD.setVisibility(View.GONE);
        ch = (CheckBox) findViewById(R.id.checkBox1);
        ch.setChecked(false);
        ch.setVisibility(View.GONE);
        btnDecrypt = (Button) findViewById(R.id.btnDecrypt);
		btnDecrypt.setOnClickListener(this);
		btnEncrypt = (Button) findViewById(R.id.btnEncrypt);
		btnEncrypt.setOnClickListener(this);
		etVidkr = (EditText) findViewById(R.id.etVidkr);
        etE2 = (EditText) findViewById(R.id.etE2);
        etN = (EditText) findViewById(R.id.etN);
        tZakr = (TextView) findViewById(R.id.tZakr);
        tVidkr = (TextView) findViewById(R.id.tVidkr);
        etZakr = (EditText) findViewById(R.id.etZakr);
        d=0;n1=0;
        
		spec = tabs.newTabSpec("tag2");
		spec.setContent(R.id.tab2);
		spec.setIndicator("Шифрування");
		tabs.addTab(spec);
		spec = tabs.newTabSpec("tag3");
		spec.setContent(R.id.tab3);
		spec.setIndicator("Дешифрування");
		tabs.addTab(spec);
		tabs.setCurrentTab(0);
		
    }
    Intent intent;
    @Override
    public void onClick(View v) {
      switch (v.getId()) {
      case R.id.check1:
    	  if(etP.getText().toString().equals("")||etQ.getText().toString().equals(""))
    	  {
    		  Toast.makeText(getBaseContext(),"Введіть дані", Toast.LENGTH_SHORT).show();
    	  }
    	  else
    	  {
    		  p = Integer.parseInt(etP.getText().toString());
    		  Log.i("p ",Integer.toString(prost(p)));
        	  q = Integer.parseInt(etQ.getText().toString());
    		  Log.i("q ",Integer.toString(prost(q)));
        	  if(prost(p)==1&&prost(q)==1){
        	  if(nod(p,q)==1)
        	  {
        		  btnCalc1.setVisibility(View.VISIBLE);
        		  tN.setVisibility(View.VISIBLE);
        		  tFi.setVisibility(View.VISIBLE);
        	  }
        	  else
        	  {
        		  Toast.makeText(getBaseContext(),"Не взаємно прості числа", Toast.LENGTH_SHORT).show();
        		  btnCalc1.setVisibility(View.GONE);
        		  tN.setVisibility(View.GONE);
        		  tFi.setVisibility(View.GONE);
        	  }}
        	  else
        	  {
        		  if(prost(p)!=1) {Toast.makeText(getBaseContext(),"p - не просте число", Toast.LENGTH_SHORT).show();}
        		  if(prost(q)!=1) {Toast.makeText(getBaseContext(),"q - не просте число", Toast.LENGTH_SHORT).show();}
        	  
        	  }
    	  }
        break;
      case R.id.calc1:
    	  n1=p*q;
    	  Fi=(p-1)*(q-1);
    	  String t1 = tN.getText().toString()+n1;
    	  tN.setText(t1);
    	  String t2 = tFi.getText().toString()+Fi;
    	  tFi.setText(t2);
    	  etE1.setVisibility(View.VISIBLE);
    	  btnCheck2.setVisibility(View.VISIBLE);
        break;
      case R.id.check2:
    	  if(etE1.getText().toString().equals(""))
    	  {
    		  Toast.makeText(getBaseContext(),"Введіть дані", Toast.LENGTH_SHORT).show();
    	  }
    	  else
    	  {
    		  e1 = Integer.parseInt(etE1.getText().toString());
        	  if(nod(e1,Fi)==1)
        	  {
        		  btnCalc2.setVisibility(View.VISIBLE);
        		  tD.setVisibility(View.VISIBLE);
        		  ch.setVisibility(View.VISIBLE);
        	  }
        	  else
        	  {
        		  Toast.makeText(getBaseContext(),"Не взаємно прості числа", Toast.LENGTH_SHORT).show();
        		  btnCalc2.setVisibility(View.GONE);
        		  tD.setVisibility(View.GONE);
        		  ch.setVisibility(View.GONE);
        	  }
    	  }

        break;
      case R.id.calc2:
    	  for(int i=0;;i++)
    	  {
    		  if(5*i%Fi==1){
    			  d=i;break;
    		  }
    	  }
    	  String t3="d=";
    	  tD.setText(t3);
    	  if(ch.isChecked())
    	  {
    		  t3 = tD.getText().toString()+d;
        	  tD.setText(t3); 
    	  }
    	  else
    	  {
    		  t3 = tD.getText().toString();
    		  for(int i=0;i<Integer.toString(d).length();i++)
    		  {
    		  t3=t3+"*";
    		  }
    		  tD.setText(t3);
    	  }
    	  break;
      case R.id.checkBox1:
 		 t3="d=";
 		 tD.setText(t3);
 		if(ch.isChecked()) {
 			t3 = tD.getText().toString()+d;
 	       	  tD.setText(t3);
 			
 		}
 		else
 		{
 			t3 = tD.getText().toString();
			 for(int i=0;i<Integer.toString(d).length();i++)
   		  {
   		  t3 = t3+"*";
   		  }
   		  tD.setText(t3); 
 		}
 		break;
    case R.id.btnDecrypt:
    	if(etVidkr.getText().toString().equals("")||etE2.getText().toString().equals("")||etN.getText().toString().equals(""))
  	  {
    		
  		  Toast.makeText(getBaseContext(),"Введіть дані", Toast.LENGTH_SHORT).show();
  	  }
  	  else
  	  {
  		vidkr1=etVidkr.getText().toString();
  		e2 = Integer.parseInt(etE2.getText().toString());
  	    n2 = Integer.parseInt(etN.getText().toString());
  	    String alfavit = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz 0123456789";
  	    ArrayList<BigInteger> nom_text = new ArrayList<BigInteger>();
  	    int i,j;
  	    for(i=0;i<vidkr1.length();i++)
  		for(j=0;j<alfavit.length();j++)
  		{if(vidkr1.charAt(i)==alfavit.charAt(j)) {
  			nom_text.add(BigInteger.valueOf(j));
  		}}
  	  BigInteger tmp,t; int tg;
  	  ArrayList<Integer> nom_shifr = new ArrayList<Integer>();
  	    zakr_text1 = "";
  	    for(i=0;i<nom_text.size();i++)
  	    {
  	    	tmp=nom_text.get(i).pow(e2);
  	    	t=tmp.mod(BigInteger.valueOf(n2));
  	    	tg = Integer.parseInt(t.toString());
  	    	nom_shifr.add(tg);
  	    }

  		for(i=0;i<nom_shifr.size();i++)
  			{zakr_text1=zakr_text1+nom_shifr.get(i)+" ";}
  	  String t4="Шифротекст:"+zakr_text1;
    	  tZakr.setText(t4);
  	  }
    	break;
    case R.id.btnEncrypt:
    	if(etZakr.getText().toString().equals(""))
    	  {
      		
    		  Toast.makeText(getBaseContext(),"Введіть дані", Toast.LENGTH_SHORT).show();
    	  }
    	  else
    	  {
    		  if(d==0||n1==0)
        	  {
          		
        		  Toast.makeText(getBaseContext(),"Проведіть спочатку підготовку!", Toast.LENGTH_SHORT).show();
        	  }
        	  else
        	  {
    		  shifrotext = etZakr.getText().toString();
    		  String alfavit = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz 0123456789";
    		  ArrayList<BigInteger> str_shifre = new ArrayList<BigInteger>(); 
    		  int i,j;
    	  	   String[] str_shifr=shifrotext.split(" ");
    	  	  BigInteger tmp; int tg;
    	  	ArrayList<Integer> nom_text2 = new ArrayList<Integer>(); 
    	  	open_text="";
    	  	for(i=0;i<str_shifr.length;i++)
      	    {
    	  		str_shifre.add(BigInteger.valueOf(Long.parseLong(str_shifr[i])));
      	    }
    	  	BigInteger t;
    	  	for(i=0;i<str_shifre.size();i++)
      	    {
    	  		Log.i(Integer.toString(i)+"= ",str_shifre.get(i).toString());
    	  	tmp=str_shifre.get(i).pow(d);
    	  	Log.i("tmp= ",tmp.toString());
    	  	t=tmp.mod(BigInteger.valueOf(n1));
    	  	Log.i("t= ",t.toString());
    	  tg = Integer.parseInt(t.toString());	
    	  nom_text2.add(tg);
      	    }
      		for(i=0;i<nom_text2.size();i++)
      			for(j=0;j<alfavit.length();j++)
      			{if(nom_text2.get(i)==j) open_text=open_text+alfavit.charAt(j);}
      	  String t4="Відкритий текст:"+open_text;
        	  tVidkr.setText(t4);
        	  }
    	  }
      }
    }
    public static int nod(int a, int b) {
        if (b == 0) return a;
        int x = a % b;
        return nod(b, x);
    }
    
	public static int prost(int m){
		int fl=0;
		for(int i=1;i<Math.sqrt(m);i++)
		{
			if(m%i==0) fl++;
		}
		return fl;
	}	}