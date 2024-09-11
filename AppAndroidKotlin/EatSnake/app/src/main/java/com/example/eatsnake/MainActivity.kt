package com.example.eatsnake

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.eatsnake.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .setAnchorView(R.id.fab).show()
        }

        val viewModel = ViewModelProvider(this).get(SnakeViewModel::class.java)

        viewModel.body.observe(this, Observer{
            binding.content.gameView.snakeBody = it
            binding.content.gameView.invalidate()

        })
        viewModel.gameStatus.observe(this, Observer { gameStatus ->
            if (gameStatus == GameStatus.GAME_OVER)
            {
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("Game")
                    .setMessage("Game Over")
                    .setPositiveButton("OK", null)
                    .show()

            }

        })
        viewModel.apple.observe(this, Observer{
            binding.content.gameView.apple = it
            binding.content.gameView.invalidate()
        })
        viewModel.score.observe(this, Observer{
            binding.content.score.setText(it.toString())
        })
        viewModel.start()
        binding.content.up.setOnClickListener{
            viewModel.move(Direction.Up)
        }
        binding.content.down.setOnClickListener{
            viewModel.move(Direction.Down)
        }
        binding.content.left.setOnClickListener{
            viewModel.move(Direction.Left)
        }
        binding.content.right.setOnClickListener{
            viewModel.move(Direction.Right)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
}