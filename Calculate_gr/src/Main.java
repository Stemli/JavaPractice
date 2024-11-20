import java.util.ArrayList;

/* Кривая, но рабочая программа для расчёта необходимого веса всех продуктов по рецепту,
 если вы хотите приготовить порцию больше или меньше, а подсчитывать все ингредиенты супер впадлу.
*/
class Product {
    String productName;

    Product(String productName) {
        this.productName = productName;
    }

    public String toString() {
        return productName;
    }
}

public class Main {
    // Метод для добавления продукта
    public static void addProduct(ArrayList<Product> products, String addProduct) {
        products.add(new Product(addProduct));
    }

    public static void addWeight(ArrayList<Integer> weight, int addWeight) {
        weight.add(addWeight);
    }

    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Integer> weight = new ArrayList<>();

        /* Здесь мы добавляем продукты и их вес, не сложно.
         Просто копируем с сайта рецептов и вбиваем по примеру
         */

        // Добавляем продукты
        addProduct(products, "Мука");
        addProduct(products, "Творог");
        addProduct(products, "Зелень");

        // Добавляем веса
        addWeight(weight, 200);
        addWeight(weight, 400);
        addWeight(weight, 100);

        // Выводим содержимое списка до вычислений
        System.out.println("\nПродукты: " + products);
        System.out.println("Вес Продуктов по рецепту " + weight);

        /* CoreWeight - первое число. Это вес на который вы будете опираться из рецепта, да пустим на "творог",
            Его и вбиваем.
            currentWeight - второе число. Это вес этого продукта в наличии, да пустим аж целый килограмм,
            в моём случае
         */

        calculated calc = new calculated(400, 1000);
        calc.calculate(weight);
        System.out.println("\nПо нашим подсчётам вам потребуется");

        // Выводим содержимое списка после вычислений
        System.out.println("\nПродукты: " + products);
        System.out.println("Необходимый вес продуктов " + weight);
    }
}

/* Программа перебирает массив в поисках coreWeight, делит имеющийся вес на рецепторный
    и умножает Частное на остальные элементы массива. Всё гига-просто.
 */

class calculated {
    int coreWeight;
    int currentWeight;

    public calculated(int coreWeight, int currentWeight) {
        this.coreWeight = coreWeight;
        this.currentWeight = currentWeight;
    }

    public void calculate(ArrayList<Integer> weight) {
        for (int elements : weight) {
            if (elements == coreWeight) {
                double ratio = (double) currentWeight / coreWeight;

                // Изменяем значения в массиве
                for (int i = 0; i < weight.size(); i++) {
                    weight.set(i, (int)(weight.get(i) * ratio)); // Обновляем значение
                }
                return; // Выходим из метода после нахождения первого совпадения
            }
        }
        System.out.println("Элемент, равный coreWeight (" + coreWeight + "), не найден.");
    }
}