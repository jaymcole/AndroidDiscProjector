package jasoncole.discprojector

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView

import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        spinner_speed.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Array(14, { i -> (i + 1) }))
        spinner_glide.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Array(7, { i -> (i + 1) }))
        spinner_turn.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Array(7, { i -> (i + 1) - 6 }))
        spinner_fade.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Array(6, { i -> (i + 1) - 1 }))

        spinner_speed.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val value = spinner_speed.selectedItem
                ProjectionCanvas.setSpeed(Integer.parseInt(value.toString()))
                canvas_1.calculatePath()
                canvas_2.calculatePath()
                canvas_1.invalidate()
                canvas_2.invalidate()
            }

        }

        spinner_glide.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val value = spinner_glide.selectedItem
                ProjectionCanvas.setGlide(Integer.parseInt(value.toString()))
                canvas_1.calculatePath()
                canvas_2.calculatePath()
                canvas_1.invalidate()
                canvas_2.invalidate()
            }

        }

        spinner_turn.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val value = spinner_turn.selectedItem
                ProjectionCanvas.setTurn(Integer.parseInt(value.toString()))
                canvas_1.calculatePath()
                canvas_2.calculatePath()
                canvas_1.invalidate()
                canvas_2.invalidate()
            }

        }

        spinner_fade.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val value = spinner_fade.selectedItem
                ProjectionCanvas.setFade(Integer.parseInt(value.toString()))
                canvas_1.calculatePath()
                canvas_2.calculatePath()
                canvas_1.invalidate()
                canvas_2.invalidate()
            }

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
}
