package com.example.gotouring
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gotouring.entities.TripPackage

class TripPackageAdapter: ListAdapter<TripPackage, TripPackageAdapter.TripPackageViewHolder>(TripPackageComparator())  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripPackageViewHolder {
        return TripPackageViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TripPackageViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.PackageId.toString())
    }

    class TripPackageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tripPackageItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            tripPackageItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): TripPackageViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return TripPackageViewHolder(view)
            }
        }
    }

    class TripPackageComparator : DiffUtil.ItemCallback<TripPackage>() {
        override fun areItemsTheSame(oldItem: TripPackage, newItem: TripPackage): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: TripPackage, newItem: TripPackage): Boolean {

            return oldItem.PackageId == newItem.PackageId
        }
    }
}