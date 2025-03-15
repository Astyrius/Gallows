import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Main {
    public static void main(String[] args) {

        String[] words = new String[100];//Массив где будут храниться слова импортированные из файла
        int errorCount = 0; //количество ошибок при ведении букв
        int correctGuessCount = 0; //определяет сколько раз пользователь угадал, если он угадал все буквы то он дает запрос программе о завершении цикла и выводе что пользователь выиграл
        //______________________________________________________________________________________________________________
        //Эти строки для импорта слов из текстового файла
        File dir = new File("package");
        File file = new File(dir, "file1.txt");
        try (Scanner scanner = new Scanner(file)) {
            int index = 0;
            while (scanner.hasNext() && index < words.length) {
                words[index] = scanner.next();
                index++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        }
        //______________________________________________________________________________________________________________
        boolean continuePlaying = true;
        while (continuePlaying) {
            // Создаём коллекцию класса Set в котором будут храниться уникальные символы это что бы пользователь не вводил повторно одни и те же символы
            Set<Character> setChar = new HashSet<>();

            //_____________________________________________________________________________________________________________-
                /*
                На данном участке кода мы выбираем рандом ное слово и
                переводим его в массив и раздробляем его на символы,
                потом эти символы переводим на верхний регистр
                 */
            Random random = new Random(); //Создаём класс Random
            int randomWordIndex = random.nextInt(0, words.length); //По рандому выбираем одно слово из массива
            String word = words[randomWordIndex];//присваиваем данное слово в класс String word
            char charArray[] = word.toCharArray();//   +          Создаём массив типа char charArray и присваиваем туда наше слово
            char charLength[] = new char[charArray.length]; // Мы используем длину данного массива что бы метод StringOfWord принял его длину и показал нам сколько букв содержит данное слово но не показывать пользователю те самые буквы по ка он их не отгадает.
            for (int i = 0; i < charArray.length; i++) {
                charArray[i] = Character.toUpperCase(charArray[i]);
            }

            for (char charItem : charArray) {
                System.out.print(charItem);
            }
            //-------------------------------------------------------------------------------------------------------------_

            Graphics graphics = new Graphics(); //Класс Graphics с одним единственным методом StringOfWord
            boolean hasExceededAttempts = true; // Один из важных перемнных в коде // пока она равна true цикл while будет работать, а while свою очеред отвечает за спрос с пользователя, что бы он вел символ(букву)
            boolean isDrawingActive = true;  // bbs - если она равна true то выполняется условие по создании рисунка, а если буква(который вел пользователь) находится в слове то bbs присваивается false в том самом условии и рисунок не отображается, а после выхода из цикла ей снова мы присваиваем true.
            // и в этой итерации цикла, переменная, условием if игнорируется.
            boolean Astirius = false; // оно выступает в роли ключа если пользователь вводит одно и тоже букву больше 2 раза то мы присваиваем vvv true и после выхода из условия его проверяет равен ли он true если да то выполняется continue и даёт циклу while выпольнить строки кода снова и сам vvv мы автоматически присваиваем false.
            System.out.println();

            //______________________________________________________________________________________________________________
            //Эти строки кода отвечают за первоначальное отображение количества букв в заданном слове.
            int initialDisplayCount = 0;
            if (initialDisplayCount < 2) {
                graphics.StringOfWord(charLength);
                initialDisplayCount++;
            }
            //______________________________________________________________________________________________________________

            char wordInput;// в нём будет находится символ который будет вводить пользователь в каждой итерации.
            while (hasExceededAttempts) { //Запуск цикла hasExceededAttempts = true
                Astirius = false; // заранее присвоили false что бы когда повторном вводе буквы ей присвоили true  в самом условии
                wordInput = input();//пользователь вводит букву (Input() статический метод находящийся в том же классе что и основной код)

                //Проверка на наличие повторного ведение буквы
                for (char wordIterator : setChar) {
                    if (wordInput == wordIterator) {
                        System.out.println("Извините но вы уже вводили букву: [" + wordInput + "] ранее");
                        Astirius = true;// присваиваем true что бы (отдаем ему ключ)
                    }
                }

                setChar.add(wordInput);//Вводим символ в коллекцию уникальных элементов Set (там немогут находиться олдинаковые буквы поэтому будет проверят ест ли они в коллекции)
                if (Astirius) {//если у него есть ключ то условие выполниться и перейдёт к следующей итерации цикла while
                    continue;
                }
                isDrawingActive = true; //тоже в роли ключа на доступ

                for (int i = 0; i < charArray.length; i++) {

                    if (wordInput == charArray[i]) { // _____________________________________Если пользователь угадает букву
                        System.out.println("Правильно");
                        correctGuessCount++;//счётчик
                        charLength[i] = wordInput;
                        isDrawingActive = false;//Он угадал значит рисунок не будет показан в этой итерации

                        graphics.StringOfWord(charLength); //отоброжвет какие буквы отгодал наш пользователь
                    }
                }
                if (isDrawingActive) {
                    System.out.println("Не правильно");
                    graphics.StringOfWord(charLength);//отоброжвет какие буквы отгодал наш пользователь
                    kill(errorCount);//Рисунок
                    errorCount++;//Даёт методу kill() понять к какому рисунку нужно перейти что бы отбразить ведь рисунок это отдельные части в массиве
                } else if (correctGuessCount >= charArray.length) {//если tr равен длине массива charArray то программа говорит что он отгодал все символы находящиеся в этом массиве
                    System.out.println("Вы выиграли");
                    hasExceededAttempts = false;//В следузей иттерации цикл while небудет работать ведь пользователь отгодал
                }

                //это условия отвечает за определения количества ошибок если 5 раз ошибся программа прекращает работу
                if (errorCount == 5) {
                    System.out.print("Загаданное слово было \n:");
                    for (char wordIterator2 : charArray) {
                        System.out.print(wordIterator2);
                    }
                    System.out.println();
                    hasExceededAttempts = false;
                    break;
                }
                //______________________________________________________________
                //Простая пометка чтобы пользователь мог соорентироваться, чтобы понял какие буквы уже он вводил ранее
                System.out.print("Ранее ведённые вами буквы \n: ");
                for (char wordIterator3 : setChar) {
                    System.out.print(wordIterator3 + " ");
                }
                System.out.println();
                //_______________________________________________________________


            }
            Scanner scanner  = new Scanner(System.in);
            System.out.print("Попробывать снова?\nВведите:да <-> нет\n:");
            String playAgainChoice = scanner.nextLine();
            if(playAgainChoice.equals("да")){
                correctGuessCount = 0;
                errorCount = 0;
                hasExceededAttempts = true;
                continue;
            }
            else if(playAgainChoice.equals("нет")){
                continuePlaying = false;
            }
            else {
                System.out.println("Всего доброго");
                break;
            }

        }


    }
    //------------------------------------------------------------------------------------------------------------------

    //При вызове данного метода программа требует от пользователя, что бы он ввел букву
    public static char input(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ведите букву: ");
        String m = scanner.next();
        char m1 = m.charAt(0);
        return Character.toUpperCase(m1);
    }
    //метод для создания рисунка
    public static void kill(int numberKil){
        String[] drawingOfAGallows = { "" +
                "|\n" + "|\n" + "|\n" + "|\n" + "|\n" + "|\n" + "--------",
                "_______\n" + "|    ||\n" + "|\n" + "|\n" + "|\n" + "--------",
                "______\n" + "|    ||\n" + "|   (--)\n" + "|\n" + "|\n" + "|\n" + "--------",
                "______\n" + "|    ||\n" + "|   (--)\n" + "|   /()\\\n" + "|\n" + "|\n" + "--------",
                "______\n" + "|    ||\n" + "|   (**)\n" + "|   /()\\\n" + "|    /\\\n" + "|  ПРОВАЛ\n" + "--------",

        };
        System.out.println(drawingOfAGallows[numberKil]);

    }
}
