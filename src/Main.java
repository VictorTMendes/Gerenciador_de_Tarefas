import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Lista lista = new Lista();
        int escolha;

        do {
            System.out.println("Gerenciador de tarefas: ");
            System.out.println();
            System.out.println("1. Adicionar Tarefa");
            System.out.println("2. Listar Tarefas");
            System.out.println("3. Atualizar Tarefa");
            System.out.println("4. Remover Tarefa");
            System.out.println("5. Marcar Tarefa como Concluída");
            System.out.println("6. Sair");
            System.out.println();
            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    System.out.print("Crie um título para a tarefa: ");
                    String name = scanner.nextLine();
                    System.out.print("Qual seu nível de urgência para essa tarefa? ");
                    System.out.println("1. Leve");
                    System.out.println("2. Médio");
                    System.out.println("3. Urgente");
                    int urgencyLevel = scanner.nextInt();
                    lista.addTask(name, urgencyLevel);
                    break;

                case 2:
                    lista.listTask();
                    break;

                case 3:
                    System.out.print("Digite o número da tarefa que deseja atualizar: ");
                    int updateIndex = scanner.nextInt() - 1; // Ajusta para índice zero
                    scanner.nextLine(); // Limpar o buffer
                    System.out.print("Novo título para a tarefa: ");
                    String newName = scanner.nextLine();
                    System.out.print("Novo nível de urgência: ");
                    int newUrgency = scanner.nextInt();
                    lista.updateTask(updateIndex, newName, newUrgency);
                    break;

                case 4:
                    System.out.print("Digite o número da tarefa que deseja remover: ");
                    int removeIndex = scanner.nextInt() - 1; // Ajusta para índice zero
                    lista.removeTask(removeIndex);
                    break;

                case 5:
                    System.out.print("Digite o número da tarefa que deseja marcar como concluída: ");
                    int completeIndex = scanner.nextInt() - 1; // Ajusta para índice zero
                    lista.markTaskAsCompleted(completeIndex);
                    break;

                case 6:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (escolha != 6);

        scanner.close();
    }
}

class Task {
    private String name;
    private int urgencyLevel;
    private boolean completed;

    public Task(String name, int urgencyLevel) {
        this.name = name;
        this.urgencyLevel = urgencyLevel;
        this.completed = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUrgency() {
        return urgencyLevel;
    }

    public void setUrgency(int urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markAsCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        String urgency;
        switch (urgencyLevel) {
            case 1:
                urgency = "Leve";
                break;
            case 2:
                urgency = "Médio";
                break;
            case 3:
                urgency = "Urgente";
                break;
            default:
                urgency = "Desconhecido";
        }
        return "Tarefa: " + name + " | Urgência: " + urgency + " | Concluída: " + (completed ? "Sim" : "Não");
    }
}

class Lista {
    private ArrayList<Task> tasks;

    public Lista() {
        tasks = new ArrayList<>();
    }

    public void addTask(String name, int urgencyLevel) {
        tasks.add(new Task(name, urgencyLevel));
        System.out.println("Tarefa adicionada com sucesso!");
    }

    public void listTask() {
        if (tasks.isEmpty()) {
            System.out.println("A lista de tarefas está vazia!");
        } else {
            System.out.println();
            System.out.println("Tarefas: ");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + " - " + tasks.get(i));
            }
        }
    }

    public void removeTask(int indice) {
        if (indice >= 0 && indice < tasks.size()) {
            tasks.remove(indice);
            System.out.println("Tarefa removida com sucesso!");
        } else {
            System.out.println("Número da tarefa não encontrado.");
        }
    }

    public void updateTask(int indice, String newName, int newUrgency) {
        if (indice >= 0 && indice < tasks.size()) {
            Task task = tasks.get(indice);
            task.setName(newName);
            task.setUrgency(newUrgency);
            System.out.println("Tarefa atualizada com sucesso!");
        } else {
            System.out.println("Número da tarefa não encontrado.");
        }
    }

    public void markTaskAsCompleted(int indice) {
        if (indice >= 0 && indice < tasks.size()) {
            tasks.get(indice).markAsCompleted();
            System.out.println("Tarefa marcada como concluída.");
        } else {
            System.out.println("Número da tarefa não encontrado.");
        }
    }
}