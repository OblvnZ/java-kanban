import model.*;
import controllers.TaskManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("Поехали!");
        TaskManager manager = new TaskManager();
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
        System.out.println(manager.getTasks());
        System.out.println(manager.getEpics());
        System.out.println(manager.getSubtasks());
        manager.updateTaskStatus(subtask3, TaskStatus.IN_PROGRESS);
        manager.updateTaskStatus(task1, TaskStatus.DONE);
        manager.updateTaskStatus(subtask2, TaskStatus.DONE);
        System.out.println(manager.getTasks());
        System.out.println(manager.getEpics());
        System.out.println(manager.getSubtasks());
        manager.removeTaskById(epic2.getUuid());
        manager.removeTaskById(task1.getUuid());
        System.out.println(manager.getTasks());
        System.out.println(manager.getEpics());
        System.out.println(manager.getSubtasks());
    }
}

