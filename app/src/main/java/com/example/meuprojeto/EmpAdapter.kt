package com.example.meuprojeto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmpAdapter(private val empList: ArrayList<EmpresaModelo>) :
    RecyclerView.Adapter<EmpAdapter.ViewHolder>() {
    

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.emp_list_item, parent, false)
        return ViewHolder(itemView)
        //return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentEmp = empList[position]
        holder.tvEmpName.text = currentEmp.empNome
        holder.tvEmpEmail.text = currentEmp.empEmail
        holder.tvEmpId.text = currentEmp.empId
    }

    override fun getItemCount(): Int {
        return empList.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvEmpName : TextView = itemView.findViewById(R.id.tvEmpName)
        val tvEmpEmail : TextView = itemView.findViewById(R.id.tvEmpEmail)
        val tvEmpId : TextView = itemView.findViewById(R.id.tvEmpId)


    }

}