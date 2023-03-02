package com.riyandifirman.myrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

// Membuat class ListHeroAdapter yang mewarisi fungsi dari RecyclerView.Adapter
class ListHeroAdapter(private val listHero: ArrayList<Hero>) : RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {

    // Membuat variabel onItemClickCallback bertipe OnItemClickCallback
    private lateinit var onItemClickCallback: OnItemClickCallback

    // Membuat fungsi setOnItemClickCallback yang berfungsi untuk mengatur aksi ketika item di klik
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    // Membuat fungsi onCreateViewHolder dengan parameter parent bertipe ViewGroup dan viewType bertipe Int yang berfungsi untuk mengatur tampilan item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        // Membuat variabel view yang berfungsi untuk mengatur tampilan item dengan memanggil fungsi inflate dari LayoutInflater yang berfungsi untuk mengubah layout xml menjadi view object
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero, parent, false)
        // Mengembalikan nilai ListViewHolder dengan parameter view
        return ListViewHolder(view)
    }

    // Membuat fungsi onBindViewHolder dengan parameter holder bertipe ListViewHolder dan position bertipe Int yang berfungsi untuk mengatur data yang akan ditampilkan
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        // Membuat variabel val (name, description, photo) yang berfungsi untuk mengambil data dari listHero berdasarkan posisi
        val (name, description, photo) = listHero[position]
        // Mengatur data yang akan ditampilkan
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.imgPhoto)
        holder.tvName.text = name
        holder.tvDescription.text = description

        // Membuat aksi ketika item di klik dengan memanggil fungsi setOnClickListener dari holder.itemView yang berfungsi untuk mengatur aksi ketika item di klik
        // Kemudian memanggil fungsi onItemClicked dari variabel onItemClickCallback yang berfungsi untuk mengatur aksi ketika item di klik
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listHero[holder.adapterPosition]) }
    }

    // Membuat fungsi getItemCount yang berfungsi untuk mengembalikan jumlah data yang ada di listHero
    override fun getItemCount(): Int = listHero.size

    // Membuat class ListViewHolder yang mewarisi fungsi dari RecyclerView.ViewHolder
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Membuat variabel (imgPhoto, tvName, tvDescription) bertipe ImageView dan TextView yang berfungsi untuk mengatur tampilan item
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    // Membuat interface OnItemClickCallback yang berfungsi untuk mengatur aksi ketika item di klik
    interface OnItemClickCallback {
        // Membuat fungsi onItemClicked yang berfungsi untuk mengatur aksi ketika item di klik dengan parameter data bertipe Hero
        fun onItemClicked(data: Hero)
    }
}