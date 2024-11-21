package com.aluraChallenge.literatura.utils;

public class Screen {

    public static void screenWelcome() {
        System.out.println("*****************************");
        System.out.println("   Bienvenido a LiterAlura   ");
        System.out.println("*****************************");
    }

    public static void screenOption() {
        screenSeparation();
        System.out.println("Por favor escriba la opción deseada: ");
        System.out.println("1 - Buscar Libro por titulo.");
        System.out.println("2 - Listar libros registrados.");
        System.out.println("3 - Listar autores registrados.");
        System.out.println("4 - Listar autores vivos en un determinado año.");
        System.out.println("5 - Listar libros por idioma.");
        System.out.println("6 - Listar Top 5 libros en base a descargas acumuladas.");
        System.out.println("0 - Salir.");
    }

    public static void screenGoodBye(){
        System.out.println("*****************************");
        System.out.println("¡Gracias por usar LiterAlura!");
        System.out.println("*****************************");
    }

    public static void screenInvalidOption() {
        System.out.println("*****************************************************");
        System.out.println("Opción no valida. Por favor ingrese una opción valido");
        System.out.println("*****************************************************");
    }


    public static void screenInvalidInput() {
        System.out.println("****************************");
        System.out.println(" Ingreso un valor no valido ");
        System.out.println("****************************");
    }

    public static void screenSeparation(){
        System.out.println("-------------------------------------------------------------");
    }


}
