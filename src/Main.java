import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        //2+3
        //X+V=XV
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        Scanner scn = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String exp = scn.nextLine();

        //Определяем вводные числа состоят из двух чисел или нет
        String[] arr = exp.split("\\+");
        if(arr.length>=3){
            throw new Exception("Количество операндов должно быть 2");

        }

        //Определяем арифметическое действие:
        int actionIndex=-1;
        for (int i = 0; i < actions.length; i++) {
            if(exp.contains(actions[i])){
                actionIndex = i;
                break;
            }
        }

        //Если не нашли арифметического действия, завершаем программу
        if(actionIndex==-1){
            System.out.println("Некорректное выражение");
            return;
        }
        //Делим строчку по найденному арифметическому знаку



        String[] data = exp.split(regexActions[actionIndex]);
        //Определяем, находятся ли числа в одном формате (оба римские или оба арабские)
        if(converter.isRoman(data[0]) == converter.isRoman(data[1])){
            int a,b;
            //Определяем, римские ли это числа
            boolean isRoman = converter.isRoman(data[0]);
            if(isRoman){
                //если римские, то конвертируем их в арабские
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);

            }else{
                //если арабские, конвертируем их из строки в число
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);


            }
            //выполняем с числами арифметическое действие
            int result = switch (actions[actionIndex]) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                default -> a / b;
            };


            if ((a > 10 || a < 1) || (b > 10 || b < 1)) {
                throw new Exception("Введите числа формате от 1 до 10 или от I до X ");
            }

                if (isRoman) {  //15->XV
                    if (result <= 0) {
                        throw new Exception("Римские числа не могут быть отрицательными");

                    }
                    //если числа были римские, возвращаем результат в римском числе
                    System.out.println(converter.intToRoman(result));
                } else {
                    //если числа были арабские, возвращаем результат в арабском числе
                    System.out.println(result);
                }

        }else{
            System.out.println("Числа должны быть в одном формате");
        }


    }
}