package hdriel.phm;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

	 private Context context;
	 Soldier[][] srr;
	 int size;
	 
	 String color[] = {"#F44336","#004D40","#01579B","#9C27B0","#795548", "#FFFFFF"};
     
     public CustomAdapter(Context context, Soldier[][] srr,int size) {
         this.context = context;
         this.srr = srr;
        
         this.size = size;
  }
     
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	 public View getView(int position, View convertView, ViewGroup parent) {

        View gridView;

        if (convertView == null) // if it's not recycled, initialize some attributes
        {

            gridView = new View(context);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.tab_item, parent, false);	                
        } 
        else {
            gridView = (View) convertView;
        }

        // set value into textview
        TextView image = (TextView) gridView.findViewById(R.id.imageView);
        
        
        switch(srr[position / (int) Math.sqrt(size)][position %  (int)Math.sqrt(size)].getChar()){	
        case 'c':
        	switch(srr[position/5][position%5].getNumber()){	
            case 1:
            	image.setBackgroundResource(R.drawable.c_r);
            	//image.setImageResource(R.drawable.c_r);
            	break;
            case 2:
            	image.setBackgroundResource(R.drawable.c_g);
            	//image.setImageResource(R.drawable.c_g);
            	break;
            case 3:
            	image.setBackgroundResource(R.drawable.c_b);
            	//image.setImageResource(R.drawable.c_b);
            	break;
            case 4:
            	image.setBackgroundResource(R.drawable.c_p);
            	//image.setImageResource(R.drawable.c_p);
            	break;
            case 5:
            	image.setBackgroundResource(R.drawable.c_br);
            	//image.setImageResource(R.drawable.c_br);
            	break;
            default:
            	image.setBackgroundResource(R.drawable.empty);
            	//image.setImageResource(R.drawable.empty);
            }        	
        	break;
        case 's':
        	switch(srr[position/5][position%5].getNumber()){	
        	case 1:
            	image.setBackgroundResource(R.drawable.s_r);
            	//image.setImageResource(R.drawable.s_r);
            	break;
            case 2:
            	image.setBackgroundResource(R.drawable.s_g);
            	//image.setImageResource(R.drawable.s_g);
            	break;
            case 3:
            	image.setBackgroundResource(R.drawable.s_b);
            	//image.setImageResource(R.drawable.s_b);
            	break;
            case 4:
            	image.setBackgroundResource(R.drawable.s_p);
            	//image.setImageResource(R.drawable.s_p);
            	break;
            case 5:
            	image.setBackgroundResource(R.drawable.s_br);
            	//image.setImageResource(R.drawable.s_br);
            	break;
            default:
            	image.setBackgroundResource(R.drawable.empty);
            	//image.setImageResource(R.drawable.empty);
            }        	
        	break;
        case 't':
        	switch(srr[position/5][position%5].getNumber()){	
        	case 1:
            	image.setBackgroundResource(R.drawable.t_r);
            	//image.setImageResource(R.drawable.t_r);
            	break;
            case 2:
            	image.setBackgroundResource(R.drawable.t_g);
            	//image.setImageResource(R.drawable.t_g);
            	break;
            case 3:
            	image.setBackgroundResource(R.drawable.t_b);
            	//image.setImageResource(R.drawable.t_b);
            	break;
            case 4:
            	image.setBackgroundResource(R.drawable.t_p);
            	//image.setImageResource(R.drawable.t_p);
            	break;
            case 5:
            	image.setBackgroundResource(R.drawable.t_br);
            	//image.setImageResource(R.drawable.t_br);
            	break;
            default:
            	image.setBackgroundResource(R.drawable.empty);
            	//image.setImageResource(R.drawable.empty);
            }        	
        	break;
        case 'e':
        	switch(srr[position/5][position%5].getNumber()){	
        	case 1:
            	image.setBackgroundResource(R.drawable.e_r);
            	//image.setImageResource(R.drawable.e_r);
            	break;
            case 2:
            	image.setBackgroundResource(R.drawable.e_g);
            	//image.setImageResource(R.drawable.e_g);
            	break;
            case 3:
            	image.setBackgroundResource(R.drawable.e_b);
            	//image.setImageResource(R.drawable.e_b);
            	break;
            case 4:
            	image.setBackgroundResource(R.drawable.e_p);
            	//image.setImageResource(R.drawable.e_p);
            	break;
            case 5:
            	image.setBackgroundResource(R.drawable.e_br);
            	//image.setImageResource(R.drawable.e_br);
            	break;
            default:
            	image.setBackgroundResource(R.drawable.empty);
            	//image.setImageResource(R.drawable.empty);
            }        	
        	break;
        case 'h':
        	switch(srr[position/5][position%5].getNumber()){	
        	case 1:
            	image.setBackgroundResource(R.drawable.h_r);
            	//image.setImageResource(R.drawable.h_r);
            	break;
            case 2:
            	image.setBackgroundResource(R.drawable.h_g);
            	//image.setImageResource(R.drawable.h_g);
            	break;
            case 3:
            	image.setBackgroundResource(R.drawable.h_b);
            	//image.setImageResource(R.drawable.h_b);
            	break;
            case 4:
            	image.setBackgroundResource(R.drawable.h_p);
            	//image.setImageResource(R.drawable.h_p);
            	break;
            case 5:
            	image.setBackgroundResource(R.drawable.h_br);
            	//image.setImageResource(R.drawable.h_br);
            	break;
            default:
            	image.setBackgroundResource(R.drawable.empty);
            	//image.setImageResource(R.drawable.empty);
            }        	
        	break;
        default:
        	image.setBackgroundResource(R.drawable.empty);
        	//image.setImageResource(R.drawable.empty);   	        	
        }
              
        return gridView;
    }
	
}
