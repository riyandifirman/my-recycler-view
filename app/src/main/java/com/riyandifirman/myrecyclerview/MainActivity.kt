package com.riyandifirman.myrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    // Deklarasi RecyclerView dan ArrayList untuk menampung data Hero
    private lateinit var rvHeroes: RecyclerView
    private val list = ArrayList<Hero>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Mengatur tampilan RecyclerView dengan memanggil fungsi setHasFixedSize dari RecyclerView
        rvHeroes = findViewById(R.id.rv_heroes)
        rvHeroes.setHasFixedSize(true)

        // Menambahkan data ke ArrayList dengan memanggil fungsi getListHeroes yang berfungsi untuk mengambil data dari resource
        list.addAll(getListHeroes())
        // Menampilkan data ke RecyclerView dengan memanggil fungsi showRecyclerList yang berfungsi untuk menampilkan data ke RecyclerView
        showRecyclerList()
    }

    // Membuat fungsi onCreateOptionsMenu yang berfungsi untuk menampilkan menu di ActionBar dengan parameter menu bertipe Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Membuat variabel menuInflater yang berfungsi untuk mengatur tampilan menu dengan memanggil fungsi inflate dari MenuInflater yang berfungsi untuk mengubah layout xml menjadi view object
        menuInflater.inflate(R.menu.menu_main, menu)
        // Mengembalikan nilai true untuk menampilkan menu di ActionBar dengan memanggil fungsi super.onCreateOptionsMenu(menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Membuat fungsi onOptionsItemSelected yang berfungsi untuk menangani aksi ketika menu di ActionBar diklik dengan parameter item bertipe MenuItem
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Membuat percabangan untuk menangani aksi ketika menu di ActionBar diklik
        when (item.itemId) {
            R.id.action_list -> {
                // Membuat percabangan untuk menangani aksi ketika menu List di ActionBar diklik
                rvHeroes.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                // Membuat percabangan untuk menangani aksi ketika menu Grid di ActionBar diklik
                rvHeroes.layoutManager = GridLayoutManager(this, 2)
            }
        }
        // Mengembalikan nilai true untuk menampilkan menu di ActionBar dengan memanggil fungsi super.onOptionsItemSelected(item)
        return super.onOptionsItemSelected(item)
    }

    // Membuat fungsi getListHeroes yang berfungsi untuk mengambil data dari resource
    private fun getListHeroes(): ArrayList<Hero> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        // Membuat ArrayList untuk menampung data Hero
        val listHero = ArrayList<Hero>()
        // Membuat perulangan for untuk menambahkan data ke ArrayList
        for (i in dataName.indices) {
            val hero = Hero(dataName[i], dataDescription[i], dataPhoto[i])
            listHero.add(hero)
        }
        // Mengembalikan nilai listHero
        return listHero
    }

    // Membuat fungsi showRecyclerList yang berfungsi untuk menampilkan data ke RecyclerView
    private fun showRecyclerList() {
        // Menampilkan data ke RecyclerView dengan memanggil fungsi layoutManager dan adapter dari RecyclerView serta membuat objek ListHeroAdapter yang berfungsi untuk menampilkan data ke RecyclerView
        rvHeroes.layoutManager = LinearLayoutManager(this)
        // Membuat objek listHeroAdapter yang berfungsi untuk menampilkan data ke RecyclerView
        val listHeroAdapter = ListHeroAdapter(list)
        // Menampilkan data ke RecyclerView dengan memanggil fungsi adapter dari RecyclerView
        rvHeroes.adapter = listHeroAdapter

        // Membuat objek OnItemClickCallback yang berfungsi untuk menangani aksi ketika item di RecyclerView diklik
        listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback {
            // Membuat fungsi onItemClicked yang berfungsi untuk menangani aksi ketika item di RecyclerView diklik dengan parameter data bertipe Hero
            override fun onItemClicked(data: Hero) {
                // Menampilkan data ke RecyclerView dengan memanggil fungsi showSelectedHero yang berfungsi untuk menampilkan data ke RecyclerView
                showSelectedHero(data)
            }
        })
    }

    // Membuat fungsi showSelectedHero yang berfungsi untuk menampilkan data ke RecyclerView dengan parameter hero bertipe Hero
    private fun showSelectedHero(hero: Hero) {
        // Menampilkan data ke RecyclerView dengan memanggil fungsi Toast.makeText yang berfungsi untuk menampilkan pesan sementara di layar
        // Fungsi toast adalah fungsi yang membutuhkan 3 parameter yaitu context, pesan, dan durasi pesan yang ditampilkan di layar
        Toast.makeText(this, "Kamu memilih ${hero.name}", Toast.LENGTH_SHORT).show()
    }
}