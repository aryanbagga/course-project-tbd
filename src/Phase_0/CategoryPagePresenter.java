package Phase_0;

public class CategoryPagePresenter {

    public CategoryPagePresenter(){
    }


    public void availableOptions(){
        System.out.println("--------------------");
        System.out.print("Your options:\n" +
                "1, Back\n" +
                "2. Add Task to new Category:\n" +
                "3. Add Task to existing Category:\n" +
                "4. View Categories \n" +
                "Your answer here: ");
    }

    public void giveNewCategoryName(){
        System.out.println("--------------------");
        System.out.print("Enter Category Title:\n");
    }

    public void giveCategoryDetail(){
        System.out.println("--------------------");
        System.out.println("Enter Category Detail:");
    }

    public void CategoryAdd(){
        System.out.println("Your Category has been created and added");
    }

    public void CategoryNotPresent(){
        System.out.println("Sorry, the Category is not there in our database");
    }

    public void displayCategory(){
        System.out.println("The Categories are :");
    }
}
