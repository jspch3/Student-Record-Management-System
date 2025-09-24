package Task2;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementSystem {


    private static ArrayList<Student> studentList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        while (true) {
            System.out.println("\n--- Student Record Management System ---");
            System.out.println("1. Add a new Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update a Student's Record");
            System.out.println("4. Delete a Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            try {
                choice = Integer.parseInt(scanner.nextLine()); 

                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        viewStudents();
                        break;
                    case 3:
                        updateStudent();
                        break;
                    case 4:
                        deleteStudent();
                        break;
                    case 5:
                        System.out.println("Exiting application. Goodbye! 👋");
                        scanner.close(); 
                        return; 
                    default:
                        System.out.println("Invalid choice! Please enter a number between 1 and 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    /**
     * Finds a student by their ID.
     * @param id The ID of the student to find.
     * @return The Student object if found, otherwise null.
     */
    private static Student findStudentById(int id) {
        for (Student student : studentList) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null; 
    }

    
    private static void addStudent() {
        System.out.println("\n--- Add a New Student ---");
        try {
            System.out.print("Enter Student ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            

            if (findStudentById(id) != null) {
                System.out.println("Error: A student with ID " + id + " already exists.");
                return;
            }

            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Student Marks: ");
            double marks = Double.parseDouble(scanner.nextLine());

            studentList.add(new Student(id, name, marks));
            System.out.println("✅ Student added successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid input for ID or Marks. Please enter numbers.");
        }
    }

    
    private static void viewStudents() {
        System.out.println("\n--- All Student Records ---");
        if (studentList.isEmpty()) {
            System.out.println("No student records found. The list is empty.");
        } else {

            for (Student student : studentList) {
                System.out.println(student); 
            }
        }
    }

    
    private static void updateStudent() {
        System.out.println("\n--- Update a Student's Record ---");
        System.out.print("Enter the ID of the student to update: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Student studentToUpdate = findStudentById(id);

            if (studentToUpdate != null) {
                System.out.print("Enter new name (current: " + studentToUpdate.getName() + "): ");
                String newName = scanner.nextLine();
                
                System.out.print("Enter new marks (current: " + studentToUpdate.getMarks() + "): ");
                double newMarks = Double.parseDouble(scanner.nextLine());

  
                studentToUpdate.setName(newName);
                studentToUpdate.setMarks(newMarks);
                System.out.println("✅ Student record updated successfully!");
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a number.");
        }
    }


    private static void deleteStudent() {
        System.out.println("\n--- Delete a Student ---");
        System.out.print("Enter the ID of the student to delete: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            
  
            boolean removed = studentList.removeIf(student -> student.getId() == id);
            
            if (removed) {
                System.out.println("✅ Student with ID " + id + " was deleted successfully.");
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a number.");
        }
    }
}
