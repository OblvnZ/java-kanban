import model.*;
import controllers.InMemoryTaskManager;


import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println("Поехали!");
        Managers qwe = new Managers();
        InMemoryTaskManager manager = (InMemoryTaskManager) qwe.getDefault();

        Task task1 = new Task("Сходить за покупками", "Купить необходимые на лето вещи");
        Task task2 = new Task("Собрать шкаф", "Собрать шкаф опираясь на инструкцию");
        Epic epic1 = new Epic("Переезд", "Переехать в другой город");
        Subtask subtask1 = new Subtask("Собрать вещи", "Упаковать вещи по коробкам", epic1);
        Subtask subtask2 = new Subtask("Подготовить собаку к переезду", "Собрать все вещи и игрушки собаки", epic1);
        Epic epic2 = new Epic("Закончить ВУЗ", "Полуяить диплом");
        Subtask subtask3 = new Subtask("Сдать дипломную работу", "Написать и защитить дипломную работу", epic2);
        manager.addTask(task1);
        manager.addTask(task2);
        manager.addTask(epic1);
        manager.addTask(subtask1);
        manager.addTask(subtask2);
        manager.addTask(epic2);
        manager.addTask(subtask3);
        manager.getTaskById(epic1.getUuid());
        manager.getTaskById(task1.getUuid());
        manager.getTaskById(subtask1.getUuid());
        manager.getTaskById(task2.getUuid());
        manager.getTaskById(epic2.getUuid());
    }
}

