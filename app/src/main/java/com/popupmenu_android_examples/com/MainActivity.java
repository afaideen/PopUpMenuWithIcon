package com.popupmenu_android_examples.com;

//import android.app.Activity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	Button button;
	PopupMenu popupmenu ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
       button = (Button)findViewById(R.id.button1);
       
       button.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
         
			PopMenuDisplay();
			
		}
	});
    }

    @SuppressLint("RestrictedApi")
    public void PopMenuDisplay() {
    	
    	popupmenu = new PopupMenu(MainActivity.this, button);
        Menu menu = popupmenu.getMenu();
        MenuBuilder menuBuilder =new MenuBuilder(this);
//		popupmenu.getMenuInflater().inflate(R.menu.pop_up_menu, menu);  //without icon
//		popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//
//             public boolean onMenuItemClick(MenuItem item) {
//
//                Toast.makeText(MainActivity.this, item.getTitle(),Toast.LENGTH_LONG).show();
//
//                return true;
//             }
//        });
//		popupmenu.show();

        //ref https://stackoverflow.com/questions/15454995/popupmenu-with-icons
        popupmenu.getMenuInflater().inflate(R.menu.pop_up_menu, menuBuilder);        //with icon
        MenuPopupHelper menuHelper = new MenuPopupHelper(MainActivity.this, menuBuilder, button);
        menuHelper.setForceShowIcon(true);
        menuHelper.setGravity(Gravity.END);
        menuHelper.show();

        // Set Item Click Listener
        menuBuilder.setCallback(new MenuBuilder.Callback() {
            @Override
            public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                Toast.makeText(MainActivity.this, item.getTitle(),Toast.LENGTH_LONG).show();
                return true;
            }

            @Override
            public void onMenuModeChange(MenuBuilder menu) {}
        });
		
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_main_menus,menu);

        if(menu instanceof MenuBuilder){

            MenuBuilder menuBuilder = (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }

        return true;
    }
}
