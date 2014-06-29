package com.example.videoplayer;



import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;

public class MainActivity extends Activity {
	static View frame1;
	static player2 textureView;
	static boolean pause=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	@Override
    protected void onDestroy() {
		pause=false;
		super.onDestroy();
	}
	@Override
	public void onPause() {
		pause=true;
		super.onPause();
	}
	@Override
	public void onResume() {
		if ((player2.sf!=null)&&(pause==true)&&(textureView.getSurfaceTexture()==null)) {
		    textureView.setSurfaceTexture(player2.sf);}
		super.onResume();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment  {
		
		
		public PlaceholderFragment() {}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			frame1=rootView.findViewById(R.id.frame1);
			
			
			textureView = (player2)rootView.findViewById(R.id.textureView1);
			textureView.setVideoPath("http://master255.no-ip.org/res/Клипы/S/SKRILLEX/Skrillex - Summit (feat. Ellie Goulding) [Video by Pilerats].mp4");
			textureView.setMediaController(new mediac(getActivity(), frame1));
			if (player2.sf != null) {
	        	if ((pause==false)&&(textureView.getSurfaceTexture()==null)) {
	        		textureView.setSurfaceTexture(player2.sf);}
	        }
			
			textureView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
	            @Override
	            public void onPrepared(final MediaPlayer mp) {
	                textureView.start();
	            }
	        });
			return rootView;
		}
	    public class mediac extends MediaController
	    {
	    	
	        public mediac(Context context, View anchor)
	        {
	            super(context);
	            super.setAnchorView(anchor);
	        }

	        @Override
	        public void setAnchorView(View view)
	        {}
	        

	        
	    }
	}

}
