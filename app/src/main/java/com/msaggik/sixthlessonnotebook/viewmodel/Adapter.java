package com.msaggik.sixthlessonnotebook.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.msaggik.sixthlessonnotebook.R;
import com.msaggik.sixthlessonnotebook.model.Notebook;
import com.msaggik.sixthlessonnotebook.view.UpdateActivity;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    // поля адаптера
    private Context context; // поле для контекста
    private Activity activity; // поле для активности
    private List<Notebook> notesList; // поле для всех записей
    private List<Notebook> newList; // поле для новой записи

    // конструктор
    public Adapter(Context context, Activity activity, List<Notebook> notesList) {
        this.context = context;
        this.activity = activity;
        this.notesList = notesList;
        newList = new ArrayList<>(notesList);
    }

    // метод onCreateViewHolder() возвращает объект ViewHolder(), который будет хранить данные по одному объекту Notebook
    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // трансформация layout-файла во View-элемент
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    // метод onBindViewHolder() выполняет привязку объекта ViewHolder к объекту Notebook по определенной позиции
    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        holder.title.setText(notesList.get(position).getTitle());
        holder.description.setText(notesList.get(position).getDescription());

        // обработаем нажатие на контейнер notes_recycler_view
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // задание переключения на новый экран
                Intent intent = new Intent(context, UpdateActivity.class);
                // передача данных в новую активити
                intent.putExtra("title", notesList.get(position).getTitle());
                intent.putExtra("description", notesList.get(position).getDescription());
                intent.putExtra("id", notesList.get(position).getId());
                // старт перехода
                activity.startActivity(intent);
            }
        });
    }

    // метод getItemCount() возвращает количество объектов в списке
    @Override
    public int getItemCount() {
        return notesList.size();
    }


    // созданный статический класс ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // поля представления
        TextView title, description;
        ConstraintLayout mLayout;

        // конструктор класса ViewHolder с помощью которого мы связываем поля и представление notes_recycler_view.xml
        ViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.title);
            description = view.findViewById(R.id.description);
            mLayout = view.findViewById(R.id.mLayout);
        }
    }
}
