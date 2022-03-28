package com.example.semana10_agenda_juan_carlos_mejia_morales.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.semana10_agenda_juan_carlos_mejia_morales.R;
import com.example.semana10_agenda_juan_carlos_mejia_morales.Verificar;
import com.example.semana10_agenda_juan_carlos_mejia_morales.entidades.Contactos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaContactosAdapter extends RecyclerView.Adapter<ListaContactosAdapter.ContactoViewHolder> {

    ArrayList<Contactos> listaContactos;
    ArrayList<Contactos> listaInicial;

    public ListaContactosAdapter(ArrayList<Contactos> listaContactos) {
        this.listaContactos = listaContactos;
        listaInicial = new ArrayList<>();
        listaInicial.addAll(listaContactos);
    }


    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_de_contactos, null, false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        holder.tvwNombre.setText(listaContactos.get(position).getNombre());
        holder.tvwTelefono.setText(listaContactos.get(position).getTelefono());
        holder.tvwCorreo.setText(listaContactos.get(position).getCorreo());
    }
    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            listaContactos.clear();
            listaContactos.addAll(listaInicial);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Contactos> collecion = listaContactos.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaContactos.clear();
                listaContactos.addAll(collecion);
            } else {
                for (Contactos c : listaInicial) {
                    if (c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaContactos.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {
        TextView tvwNombre,tvwTelefono,tvwCorreo;
        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvwNombre = itemView.findViewById(R.id.tvNombre);
            tvwTelefono = itemView.findViewById(R.id.tvTelefono);
            tvwCorreo = itemView.findViewById(R.id.tvCorreo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, Verificar.class);
                    intent.putExtra("ID", listaContactos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}