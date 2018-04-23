package hdriel.phm;


import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


@SuppressLint("NewApi") 
public class MainActivity extends ActionBarActivity {

	Menu menu;
	GridView gridview;
	Button btnReset;
	ImageView star_a, star_b;
	TextView orders , title;
	CheckBox inc_diag;
	MediaPlayer mplayWin, mClick, mCheckBoxClick, mChit;
	AudioManager audio;
	CustomAdapter adapter;
	ShowSoldiers show_soldiers;
	Context c;
	int size = 5;
	int x1 , y1 , x2 , y2 ;
	boolean includeDiags;
	boolean sound;
	boolean starAshow, starBshow;
	boolean addAlreadyItemMenu ;
	boolean doubleBackToExitPressedOnce;
	RotateAnimation anim;
	Handler mHandler = new Handler();
	static final int RESET_STAR = 1;
	
	SharedPreferences sharedPref;
	final String SAVE = "save data", 
			     RANKS = "the sing of shapes", 
			     NUMBERS = "the color of the shapes", 
			     INCLUDE_DIAGS = "boolean object to check with the diagonals",
			     SOUND="sound", 
			     STAR_A = "show the star A", 
			     STAR_B = "show the star B",
			     ITEM_ADDED = "the reset item menu already added";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        x1 = y1 = x2 = y2 = -1;
        show_soldiers = new ShowSoldiers(size);
        c = this;
        
        sharedPref = getSharedPreferences(SAVE, MODE_PRIVATE);            
        int count = 0;
	    for (int i = 0; i < size; i++)	    
	    	for(int j = 0 ; j < size; j++)
	    	{
	    		show_soldiers.setSoldierInArr(i, j, 
	    				(char)(sharedPref.getString("charValue_"+ count, "" + show_soldiers.getSoldiersArray()[i][j].getChar())).charAt(0) , 
	    				       sharedPref.getInt("IntValue_"+ count, show_soldiers.getSoldiersArray()[i][j].getNumber()) );
	    		count++;
	    	}	    	
	    
	    includeDiags = sharedPref.getBoolean(INCLUDE_DIAGS, false);
	    sound        = sharedPref.getBoolean(SOUND, true);
	    starAshow    = sharedPref.getBoolean(STAR_A, false);
	    starBshow    = sharedPref.getBoolean(STAR_B, false);
	    addAlreadyItemMenu = sharedPref.getBoolean(ITEM_ADDED, false);
	    		       
        mplayWin      =  MediaPlayer.create(this, R.raw.winner);
        mClick        =  MediaPlayer.create(this, R.raw.beep);
        mCheckBoxClick=  MediaPlayer.create(this, R.raw.beep_cb);
        mChit         =  MediaPlayer.create(this, R.raw.beep_chit);
        star_a        = (ImageView)findViewById(R.id.star_a);
        star_b        = (ImageView)findViewById(R.id.star_b);
        btnReset      = (Button)   findViewById(R.id.btn_reset);
        inc_diag      = (CheckBox) findViewById(R.id.cb_includeDiags);
        
        orders        = (TextView) findViewById(R.id.tv_orders);
        title         = (TextView) findViewById(R.id.title);
        gridview      = (GridView) findViewById(R.id.gridView_soldiers);
               
        adapter       = new CustomAdapter(c, show_soldiers.getSoldiersArray(), size*size);       
        gridview.setAdapter(adapter);
       
        anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

    	//Setup anim with desired properties
    	anim.setInterpolator(new LinearInterpolator());
    	anim.setRepeatCount(Animation.INFINITE); //Repeat animation indefinitely
    	anim.setDuration(700); //Put desired duration per anim cycle here, in milliseconds
    	
    	
        if(includeDiags)
        {
        	inc_diag.setChecked(includeDiags);
        	inc_diag.setText(getResources().getString(R.string.include_diagonals));
        	orders.setText(getResources().getString(R.string.order_with_diag));
        }
        else
        {
        	inc_diag.setChecked(includeDiags);
           	inc_diag.setText(getResources().getString(R.string.disinclude_diagonals));
        	orders.setText(getResources().getString(R.string.order_without_diag));
        }
        if(!starAshow){
        	star_a.setVisibility(View.GONE);
        }
        else{
        	star_a.setVisibility(View.VISIBLE);
        }
        if(!starBshow){
        	star_b.setVisibility(View.GONE);
        }
        else{
        	star_b.setVisibility(View.VISIBLE);
        }
        
        star_b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(c, getResources().getString(R.string.star_gold), Toast.LENGTH_LONG).show();
			}
		});
        
        star_a.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(c, getResources().getString(R.string.star_slver), Toast.LENGTH_LONG).show();
			}
		});
        
        /*
        title.setOnLongClickListener(new OnLongClickListener() { 
            @Override
            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub
            	if(sound) mChit.start();
            	show_soldiers = new ShowSoldiers();
		        adapter       = new CustomAdapter(c, show_soldiers.getSoldiersArray(), size*size);
		        gridview.setAdapter(adapter);
		        Toast.makeText(c, getResources().getString(R.string.chit),Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        */
        
        inc_diag.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v)
            {
                // TODO Auto-generated method stub 
            	stopPlayingCheckBox();
            	includeDiags = !includeDiags;
            	inc_diag.setChecked(includeDiags);
                if(includeDiags)
                {
                	inc_diag.setText(getResources().getString(R.string.include_diagonals));
                	orders.setText(getResources().getString(R.string.order_with_diag));
                }
                else
                {
                	inc_diag.setText(getResources().getString(R.string.disinclude_diagonals));
                	orders.setText(getResources().getString(R.string.order_without_diag));
                }
                if(sound) mCheckBoxClick.start();
            }
        });
        
        
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            	if(gridview.isEnabled())
            	{
            		if(x1 == -1 || y1 == -1)
    	        	{
    	            	if(x1 == position / size && y1 == position % size)
    	            	{
    	            		x1 = -1;
    	            		y1 = -1;
    	            		
    	            	}
    	            	else{
    	            		x1 = position / size;
    	               	    y1 = position % size;
    	               	    
    	               	    v.setBackgroundColor(Color.rgb(100, 100, 50));
    	            	}
    	            	
    	             }
    	             else 
    	             {
    	            	 	 x2 = position / size;
    	                	 y2 = position % size; 
    	             }            
    	             
    	        	if(show_soldiers.replaceSoldiers(x1, y1, x2, y2))
    	        	{
    	        		x1 = x2 = y1 = y2 = -1;
    	        		adapter = new CustomAdapter(c, show_soldiers.getSoldiersArray(), size*size);
    	                gridview.setAdapter(adapter);
    	        	}
    	        	
    	            if(show_soldiers.finish(includeDiags))
    	            {
    	            	
    	            	
    	            	// mark all the item in yellow
    	            	for(int i = 0; i < size*size; i++){
	            			View viewItem = gridview.getChildAt(i);
	            			try{
	            				viewItem.setBackgroundColor(Color.rgb(255, 255, 51));
	            			}
	            			catch(Exception e){
	            				
	            			}
	            			 
    	            	}
    	            	
    	            	
    	            	// stop clicks on items
    	            	gridview.setEnabled(false);
    	            	x1 = x2 = y1 = y2 = -1;
    	            	   	        	    
    	            	Toast.makeText(c, getResources().getString(R.string.well_done), Toast.LENGTH_SHORT).show();
    	        	    if(sound) mplayWin.start();
    	        	    
    	        	    btnReset.setText(getResources().getString(R.string.silent_win_sound));
    	        	    
    	        	    if(includeDiags){ // if the user solve with diagonals
    	        	    	
    	        	    	starBshow = true;
    	        	    	star_b.setVisibility(View.VISIBLE);
    	        	    	star_b.startAnimation(anim); 
    	        	    	
    	        	    	if(star_a.getVisibility() != View.VISIBLE){
    	        	    		starAshow = true;
    	        	    		star_a.setVisibility(View.VISIBLE);	
    	        	    		star_a.startAnimation(anim); 
    	        	    	}
    	        	    	if(!addAlreadyItemMenu)
    	        	    		menu.add(0,RESET_STAR,1,getResources().getString(R.string.reset_win));
    	        	    }
    	        	    else{
    	        	    	starAshow = true;
    	        	    	star_a.setVisibility(View.VISIBLE);
    	        	    	star_a.startAnimation(anim); 
    	        	    }
    	            }
            	}
            	else{
            		x1 = x2 = y1 = y2 = -1;
            	}          	
            }
        });
        
        
        btnReset.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				stopPlayingClick();
				if(sound) mClick.start();
				if(btnReset.getText().toString() == getResources().getString(R.string.silent_win_sound)){
					star_a.setAnimation(null); 
					star_b.setAnimation(null);  
				}
				if(btnReset.getText().toString() == getResources().getString(R.string.play_again) || btnReset.getText().toString() == getResources().getString(R.string.play_again_diff) ){
					show_soldiers = new ShowSoldiers(size);
			        adapter       = new CustomAdapter(c, show_soldiers.getSoldiersArray(), size*size);
			        gridview.setAdapter(adapter);
			        btnReset.setText(getResources().getString(R.string.reset_board));
			        gridview.setEnabled(true);
				}
				else if(mplayWin.isPlaying()){
					stopPlayingWin();
					if(includeDiags)
			        	btnReset.setText(getResources().getString(R.string.play_again));			        
			        else 
			        	btnReset.setText(getResources().getString(R.string.play_again_diff));
					
				}				
				else{
					btnReset.setText(getResources().getString(R.string.reset_board));
					AlertDialog.Builder builder1 = new AlertDialog.Builder(c);
					builder1.setMessage(getResources().getString(R.string.do_reset));
					builder1.setCancelable(true);

					builder1.setPositiveButton( getResources().getString(R.string.yes_sure),
					    new DialogInterface.OnClickListener() {
					        public void onClick(DialogInterface dialog, int id) {
					        	gridview.setEnabled(true);
					        	
					        	show_soldiers = new ShowSoldiers(size);
						        adapter       = new CustomAdapter(c, show_soldiers.getSoldiersArray(), size*size);
						        gridview.setAdapter(adapter);
						        
						        stopPlayingClick();
						        if(sound) mClick.start();
					            dialog.cancel();
					        }
					    });

					builder1.setNegativeButton( getResources().getString(R.string.no_sure),
					    new DialogInterface.OnClickListener() {
					        public void onClick(DialogInterface dialog, int id) {
					        	
					            dialog.cancel();
					        }
					    });

					AlertDialog alert11 = builder1.create();
					alert11.show();
					
				}
			}
		});
    }

    private void stopPlayingWin() {
        if (mplayWin != null) {
        	mplayWin.stop();
        	mplayWin.release();
        	mplayWin = null;
        	mplayWin =  MediaPlayer.create(this, R.raw.winner);
       }
    }
    
    private void stopPlayingCheckBox() {
        if (mCheckBoxClick != null) {
        	mCheckBoxClick.stop();
        	mCheckBoxClick.release();
        	mCheckBoxClick = null;
        	mCheckBoxClick =  MediaPlayer.create(this, R.raw.beep_cb);
       }
    }
    
    private void stopPlayingClick() {
        if (mClick != null) {
        	mClick.stop();
        	mClick.release();
        	mClick = null;
        	mClick =  MediaPlayer.create(this, R.raw.beep);
       }
    }
    
    @Override
    protected void onPause(){
    	super.onPause();
    	SharedPreferences sharedPref = getSharedPreferences(SAVE, MODE_PRIVATE);
	    SharedPreferences.Editor editor = sharedPref.edit();
	     
	    editor.putBoolean(INCLUDE_DIAGS, includeDiags);
	    editor.putBoolean(SOUND, sound);
	    editor.putBoolean(STAR_A, starAshow);
	    editor.putBoolean(STAR_B, starBshow);
	    editor.putBoolean(ITEM_ADDED, addAlreadyItemMenu);
	    
	    int count = 0;
	    for(int i = 0; i < size; i++)
	    {
	    	for(int j = 0; j < size; j++)
	    	{
	    		editor.putInt   ("IntValue_"  + count,       show_soldiers.getSoldiersArray()[i][j].getNumber() );
	    		editor.putString("charValue_" + count, ("" + show_soldiers.getSoldiersArray()[i][j].getChar())  );
	    		count++;
	    	}
	    }
	    
	    
	    editor.commit();
    }
    
    @Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		SharedPreferences sharedPref = getSharedPreferences(SAVE, MODE_PRIVATE);
	    SharedPreferences.Editor editor = sharedPref.edit();
	     
	    editor.putBoolean(INCLUDE_DIAGS, includeDiags);
	    editor.putBoolean(SOUND, sound);
	    editor.putBoolean(STAR_A, starAshow);
	    editor.putBoolean(STAR_B, starBshow);
	    editor.putBoolean(ITEM_ADDED, addAlreadyItemMenu);
	    
	    int count = 0;
	    for(int i = 0; i < size; i++)
	    {
	    	for(int j = 0; j < size; j++)
	    	{
	    		editor.putInt   ("IntValue_"  + count,       show_soldiers.getSoldiersArray()[i][j].getNumber() );
	    		editor.putString("charValue_" + count, ("" + show_soldiers.getSoldiersArray()[i][j].getChar())  );
	    		count++;
	    	}
	    }
	    
	    
	    editor.commit();
	    
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// TODO Auto-generated method stub	
		SharedPreferences sharedPref = getSharedPreferences(SAVE, MODE_PRIVATE);
	    SharedPreferences.Editor editor = sharedPref.edit();
	     
	    editor.putBoolean(INCLUDE_DIAGS, includeDiags);
	    editor.putBoolean(SOUND, sound);
	    editor.putBoolean(STAR_A, starAshow);
	    editor.putBoolean(STAR_B, starBshow);
	    editor.putBoolean(ITEM_ADDED, addAlreadyItemMenu);
	    
	    int count = 0;
	    for(int i = 0; i < size; i++)
	    {
	    	for(int j = 0; j < size; j++)
	    	{
	    		editor.putInt   ("IntValue_"  + count,       show_soldiers.getSoldiersArray()[i][j].getNumber() );
	    		editor.putString("charValue_" + count, ("" + show_soldiers.getSoldiersArray()[i][j].getChar())  );
	    		count++;
	    	}
	    }
	    
	    
	    editor.commit();
	    if (mHandler != null) { mHandler.removeCallbacks(mRunnable); }
	    
	    
	}
    
	private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;                       
        }
    };

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getResources().getString(R.string.kayback_again), Toast.LENGTH_SHORT).show();
        mHandler.postDelayed(mRunnable, 2000);
    }
        
  
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        this.menu = menu;
        if(sound){
        	menu.findItem(R.id.action_volume).setTitle(getResources().getString(R.string.silent));
    		mplayWin.setVolume(1,1); 
    		mClick.setVolume(1,1);
    		mCheckBoxClick.setVolume(1,1);
    		mChit.setVolume(1,1);
    	}
    	else{
    		menu.findItem(R.id.action_volume).setTitle(getResources().getString(R.string.sound));
    		mplayWin.setVolume(1,1); 
    		mClick.setVolume(0,0);
    		mCheckBoxClick.setVolume(0,0);
    		mChit.setVolume(0,0);
    	}
        
        if(starAshow && starBshow){
        	 menu.add(0,RESET_STAR,1,getResources().getString(R.string.reset_win)); 
        	 addAlreadyItemMenu = true;
        }  
        else{
        	menu.removeItem(RESET_STAR);
        	addAlreadyItemMenu = false;
        }
        	 
        return true;
    }
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
    	if(!(starAshow && starBshow)){
    		menu.removeItem(RESET_STAR);
    		addAlreadyItemMenu = false;
    	}
           	 
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	MenuItem MenuItemVolume = menu.findItem(R.id.action_volume);
    	switch (item.getItemId()) 
    	{  
        case R.id.action_settings:                
        	Toast.makeText(c, getResources().getString(R.string.about), Toast.LENGTH_LONG).show();
     	return true;
        
        case R.id.action_volume:
        	sound = !sound;
        	if(sound){
        		MenuItemVolume.setTitle(getResources().getString(R.string.silent));
        		mplayWin.setVolume(1,1); 
        		mClick.setVolume(1,1);
        		mCheckBoxClick.setVolume(1,1);
        		mChit.setVolume(1,1);
        	}
        	else{
        		MenuItemVolume.setTitle(getResources().getString(R.string.sound));
        		mplayWin.setVolume(1,1); 
        		mClick.setVolume(0,0);
        		mCheckBoxClick.setVolume(0,0);
        		mChit.setVolume(0,0);
        	}
        	return true;
        	
        case RESET_STAR:
        	AlertDialog.Builder builder1 = new AlertDialog.Builder(c);
			builder1.setMessage(getResources().getString(R.string.do_reset_win));
			builder1.setCancelable(true);
        	builder1.setPositiveButton(
        			getResources().getString(R.string.yes_win),
				    new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int id) {
				        	gridview.setEnabled(true);
				        	
				        	starAshow = false;
				        	starBshow = false;
				        	star_a.setVisibility(View.GONE);
				        	star_b.setVisibility(View.GONE);
				        	menu.removeItem(RESET_STAR);
				    		addAlreadyItemMenu = false;
				    		
					        stopPlayingClick();
					        if(sound) mClick.start();
				            dialog.cancel();
				        }
				    });

				builder1.setNegativeButton(
						getResources().getString(R.string.no_win),
				    new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int id) {
				            dialog.cancel();
				        }
				    });

				AlertDialog alert11 = builder1.create();
				alert11.show();
        	return true;
        default:        
     		return super.onOptionsItemSelected(item);
    	}
    }
}
