package Console;

import Entity.Client;
import Entity.Pet;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Console {
    private final Scanner scanner = new Scanner(System.in);

    public Console(String title) {
        this.clear();
        this.buildHeader(title);
    }

    private void buildHeader(String title) {
        int boxWidth = 40;
        int spacesBeforeTitle = (boxWidth - title.length()) / 2;
        int spacesAfterTitle = boxWidth - title.length() - spacesBeforeTitle;
        this.print("┌────────────────────────────────────────┐");
        this.print(STR."|\{" ".repeat(spacesBeforeTitle)}\{title}\{" ".repeat(spacesAfterTitle)}|");
        this.print("└────────────────────────────────────────┘");
    }

    public void clear() {
        String RESET = "\u001B[0m";
        String WHITE = "\u001B[37m";
        this.print(WHITE + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + RESET);
    }

    public Console print(String text) {
        String PURPLE = "\u001B[35m";
        System.out.println(PURPLE +text);
        return this;
    }

    public void printPetPresentation(List<Pet> pets) {
        this.print("   /\\_/")
                .print("  / o o \\   "+ "Cantidad de mascotas")
                .print(" (   \"   )  "+ "disponibles: " + pets.size())
                .print("  \\~(*)~/");

        this.print("-----------------------------------------------------------------------------");
        System.out.printf("%10s %20s %20s %20s", "ID", "NOMBRE", "ESPECIE", "COLOR");
        this.ln()
            .print("-----------------------------------------------------------------------------");
        for (int i = 0; i < pets.size(); i++) {
            System.out.format("%10s %20s %20s %20s", i, pets.get(i).getName(), pets.get(i).getKind(), pets.get(i).getColor());
            this.ln()
                .print("-----------------------------------------------------------------------------");
        }
    }

    public void printPetHistory(List<Client> clients) {
        this.print("-----------------------------------------------------------------------------");
        System.out.printf("%20s %20s %20s", "NOMBRE", "ESPECIE", "ADOPTADO POR");
        this.ln()
                .print("-----------------------------------------------------------------------------");
        for (Client client : clients) {
            System.out.format("%20s %20s %20s", client.getPet().getName(), client.getPet().getKind().toString(), client.getName());
            this.ln()
                    .print("-----------------------------------------------------------------------------");
        }
    }

    public void printPetPresentation(Pet pet) {
        this.print("   /\\_/")
                .print("  / o o \\   "+ "¡Hola!")
                .print(STR." (   \"   )  Me llamo \{pet.getName()} y soy un \{pet.getKind().toString()}")
                .print("  \\~(*)~/")
                .ln()
                .print(STR."Nací el \{pet.getBirthdate().toString()}, tengo \{pet.getAge()} añitos.")
                .print(STR."Mi piél es de color \{pet.getColor()}")
                .print(STR."La última vez que me subí a una balanza pesaba \{pet.getWeight()}").ln()
                .print(STR."Recomendaciones de cuidado: \{pet.getCareRecommendation()}").ln();
    }

    public Console ln() {
        System.out.println();
        return this;
    }

    public String in(String label) {
        this.print(label);
        return this.scanner.nextLine();
    }

    public int inInt(String label) {
        this.print(label);
        return this.scanner.nextInt();
    }

    public Boolean question(String label) {
        this.print(label);
        String response = this.in("Escriba 'si' para continuar.");
        return response.equalsIgnoreCase("si");
    }
    public void sleep(int seconds) throws InterruptedException {
        Thread.sleep(seconds* 1000L);
    }
}
