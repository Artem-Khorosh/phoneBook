package practice;

import java.util.Scanner;
import java.util.Set;

public class Main {

  public static void main(String[] args) {
    PhoneBook phoneBook = new PhoneBook();
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println("Введите номер, имя или команду:");
      String input = scanner.nextLine().trim();
      if (input.equalsIgnoreCase("EXIT")) {
        System.out.println("Программа завершена");
        break;
      }
      processCommand(input, phoneBook);
    }
    scanner.close();
  }

  private static void processCommand(String input, PhoneBook phoneBook) {
    if (phoneBook.isValidName(input)) {
      handleValidNameInput(input, phoneBook);
    } else if (phoneBook.isValidPhone(input)) {
      handleValidPhoneInput(input, phoneBook);
    } else if (input.equalsIgnoreCase("LIST")) {
      listAllContacts(phoneBook);
    } else {
      System.out.println("Неверный формат ввода");
    }
  }

  private static void handleValidNameInput(String name, PhoneBook phoneBook) {
    Scanner scanner = new Scanner(System.in);
    Set<String> existingContacts = phoneBook.getContactByName(name);
    if (!existingContacts.isEmpty()) {
      System.out.println("Введите номер телефона для абонента \"" + name + "\":");
      String phone = scanner.nextLine().trim();
      if (!phoneBook.getContactByPhone(phone).isEmpty()) {
        System.out.println("Контакт с таким номером уже существует");
        return;
      }
      phoneBook.addContact(phone, name);
    } else {
      System.out.println("Введите номер телефона для абонента \"" + name + "\":");
      String phone = scanner.nextLine().trim();
      phoneBook.addContact(phone, name);
    }
  }

  private static void handleValidPhoneInput(String phone, PhoneBook phoneBook) {
    Scanner scanner = new Scanner(System.in);
    if (!phoneBook.getContactByPhone(phone).isEmpty()) {
      System.out.println("Введите имя абонента для номера \"" + phone + "\":");
      String name = scanner.nextLine().trim();
      if (!phoneBook.getContactByName(name).isEmpty()) {
        System.out.println("Контакт с таким именем уже существует");
        return;
      }
      phoneBook.addContact(phone, name);
    } else {
      System.out.println("Введите имя абонента для номера \"" + phone + "\":");
      String name = scanner.nextLine().trim();
      phoneBook.addContact(phone, name);
    }
  }

  private static void listAllContacts(PhoneBook phoneBook) {
    Set<String> contacts = phoneBook.getAllContacts();
    for (String contact : contacts) {
      System.out.println(contact);
    }
  }

}
