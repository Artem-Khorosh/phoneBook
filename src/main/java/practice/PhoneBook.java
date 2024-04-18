package practice;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class PhoneBook {

  private Map<String, Set<String>> phonebook = new HashMap<>();

  public void addContact(String phone, String name) {
    if (isValidName(name) && isValidPhone(phone)) {
      phonebook.entrySet().removeIf(entry -> entry.getValue().contains(phone));

      phonebook.computeIfAbsent(name, k -> new TreeSet<>()).add(phone);

      System.out.println("Контакт сохранен!");
    }
  }


  public String getContactByPhone(String phone) {
    for (Map.Entry<String, Set<String>> entry : phonebook.entrySet()) {
      if (entry.getValue().contains(phone)) {
        return String.format("%s - %s", entry.getKey(), phone);
      }
    }
    return "";
  }

  public Set<String> getContactByName(String name) {
    Set<String> contacts = phonebook.getOrDefault(name, new TreeSet<>());
    Set<String> formattedContacts = new TreeSet<>();
    for (String phone : contacts) {
      formattedContacts.add(String.format("%s - %s", name, phone));
    }
    return formattedContacts;
  }

  public Set<String> getAllContacts() {
    Set<String> allContacts = new TreeSet<>();
    for (Map.Entry<String, Set<String>> entry : phonebook.entrySet()) {
      String name = entry.getKey();
      Set<String> phones = entry.getValue();
      StringBuilder phoneStringBuilder = new StringBuilder();
      for (String phone : phones) {
        phoneStringBuilder.append(phone).append(", ");
      }
      if (phoneStringBuilder.length() > 0) {
        phoneStringBuilder.setLength(phoneStringBuilder.length() - 2);
      }
      String phonesString = phoneStringBuilder.toString();
      allContacts.add(String.format("%s - %s", name, phonesString));
    }
    return allContacts;
  }

  public boolean isValidName(String name) {
    String nameRegex = "^[А-Яа-я]+$";
    return name.matches(nameRegex);
  }

  public boolean isValidPhone(String phone) {
    String phoneRegex = "\\d{11}";
    return phone.matches(phoneRegex);
  }
}