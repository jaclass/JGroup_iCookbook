package controller.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import entity.Ingredient;
import entity.PreparationStep;
import entity.Recipe;
import entity.User;
import javafx.scene.image.Image;

/**
 * Methods to deal with DB.
 * 
 * @author JGroup
 *
 */
public class DBController {
	
	/**
	 * Insert Ingredient.
	 * 
	 * @param ing Ingredient Entity to be inserted.
	 * @param recipe_id Foreign key for the PreparationStep.
	 * @return Updated rows.
	 */
	public static int insertIngredient(int recipe_id, Ingredient ing) {
		Connection conn = DBUtils.getMySqlConn();
	    PreparedStatement pstmt = null;
	    String sql = "insert into ingredient(recipe_id,ingredient_name,amount,unit,description) values(?,?,?,?,?)";
	    int ret = 0;

	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, recipe_id);
	        pstmt.setString(2, ing.getIngredientName());
	        pstmt.setDouble(3, ing.getAmount());
	        pstmt.setString(4, ing.getUnit());
	        pstmt.setString(5, ing.getDescription());
	        ret = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
			DBUtils.close(pstmt, conn);
		}
	    
	    return ret;
	}
	
	/**
	 * Update the ingredient
	 * 
	 * @param recipe_id Foreign key for the PreparationStep.
	 * @param step Order of the PreparationStep in the recipe.
	 * @param detail Updated detail.
	 * @return Updated rows.
	 */
	public static int updateIngredient(int recipe_id, String origin_name, Ingredient ing) {
		Connection conn = DBUtils.getMySqlConn();
		PreparedStatement pstmt = null;
		String sql = "update Ingredient set ingredient_name=?,amount=?,unit=?,description=? where recipe_id=? and ingredient_name=?";
		int ret = 0;
		
		try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, ing.getIngredientName());
	        pstmt.setDouble(2, ing.getAmount());
	        pstmt.setString(3, ing.getUnit());
	        pstmt.setString(4, ing.getDescription());
	        pstmt.setInt(5, recipe_id);
	        pstmt.setString(6, origin_name);
	        ret = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
			DBUtils.close(pstmt, conn);
		}
		return ret;
	}
	
	/**
	 * Delete Ingredient.
	 * 
	 * @param ing Ingredient Entity to be deleted.
	 * @param recipe_id Foreign key for the PreparationStep.
	 * @return Updated rows.
	 */
	public static int deleteIngredient(int recipe_id, Ingredient ing) {
		Connection conn = DBUtils.getMySqlConn();
	    PreparedStatement pstmt = null;
	    String sql = "delete from ingredient where recipe_id=? and ingredient_name=?";
	    int ret = 0;
	    
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, recipe_id);
	        pstmt.setString(2, ing.getIngredientName());
	        ret = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
			DBUtils.close(pstmt, conn);
		}
	    
	    return ret;
	}
	
    /**
     * Insert PreparationStep. Update the step = step + 1 behind the inserted step.
     * 
     * @param recipe_id Foreign key for the PreparationStep.
     * @param step Order of the PreparationStep in the recipe.
     * @param ps PreaprationStep to be inserted.
     * @return Updated rows.
     */
    public static int insertPreparationStep(int recipe_id, int step, PreparationStep ps) {
    	Connection conn = DBUtils.getMySqlConn();
    	PreparedStatement pstmt_search = null;
    	PreparedStatement pstmt_insert = null;
    	String search_sql = "select step from preparation_step where recipe_id=? and step>=?";
    	String update_sql = "update preparation_step set step=? where recipe_id=? and step=?";
    	String insert_sql = "insert into preparation_step(recipe_id,step,detail) values(?,?,?)";
    	ResultSet rs = null;
    	List<Integer> list = new ArrayList<>();
    	int ret = 0;
    	
    	try {
    		// Get all the step larger than insert step and sort them.
    		pstmt_search = conn.prepareStatement(search_sql);
    		pstmt_search.setInt(1, recipe_id);
    		pstmt_search.setInt(2, step);
    		rs = pstmt_search.executeQuery();
    		while(rs.next()) { 
    			list.add(rs.getInt(1));
    		}
    		Collections.sort(list);
    		Collections.reverse(list);
    		
    		Iterator<Integer> it = list.iterator();
    		while(it.hasNext()) {
    			PreparedStatement pstmt_update = null;
    			Integer target = it.next();
    			int target_step = target != null ? target : 0;
    			pstmt_update = conn.prepareStatement(update_sql);
    			pstmt_update.setInt(1, target_step + 1);
    			pstmt_update.setInt(2, recipe_id);
    			pstmt_update.setInt(3, target_step);
    			pstmt_update.executeUpdate();
    			pstmt_update.close();
    		}
    		
    		pstmt_insert = conn.prepareStatement(insert_sql);
    		pstmt_insert.setInt(1, recipe_id);
    		pstmt_insert.setInt(2, step);
    		pstmt_insert.setString(3, ps.getDetail());
    		ret = pstmt_insert.executeUpdate();
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		DBUtils.close(rs, pstmt_search, conn);
    		DBUtils.close(pstmt_insert);
    	}
    	
    	return ret;
    }
    
	/**
	 * Update the step = step + 1 behind the inserted step.
	 * 
	 * @param recipe_id Foreign key for the PreparationStep.
	 * @param step Order of the PreparationStep in the recipe.
	 * @param detail Updated detail.
	 * @return Updated rows.
	 */
	public static int updatePreparationStep(int recipe_id, int step, String detail) {
		Connection conn = DBUtils.getMySqlConn();
		PreparedStatement pstmt = null;
		String sql = "update preparation_step set detail=? where recipe_id=? and step=?";
		int ret = 0;
		
		try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, detail);
	        pstmt.setInt(2, recipe_id);
	        pstmt.setInt(3, step);
	        ret = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
			DBUtils.close(pstmt, conn);
		}
		
		return ret;
	}

	/**
	 * Delete the PreparationStep. Update the step = step - 1 behind the inserted step.
	 * 
	 * @param recipe_id Foreign key for the PreparationStep.
	 * @param step Order of the PreparationStep in the recipe.
	 * @return Updated rows.
	 */
	public static int deletePreparationStep(int recipe_id, int step) {
		Connection conn = DBUtils.getMySqlConn();
	    PreparedStatement pstmt_search = null;
	    PreparedStatement pstmt_delete = null;
	    String search_sql = "select step from preparation_step where recipe_id=? and step>?";
	    String update_sql = "update preparation_step set step=? where recipe_id=? and step=?";
	    String delete_sql = "delete from preparation_step where recipe_id=? and step=?";
	    ResultSet rs = null;
	    List<Integer> list = new ArrayList<>();
	    int ret = 0;
	    
	    try {
	    	pstmt_delete = conn.prepareStatement(delete_sql);
	        pstmt_delete.setInt(1, recipe_id);
	        pstmt_delete.setInt(2, step);
	        ret = pstmt_delete.executeUpdate();
	        
	        // Get all the steps smaller than insert step and sort them.
	    	pstmt_search = conn.prepareStatement(search_sql);
	    	pstmt_search.setInt(1, recipe_id);
	    	pstmt_search.setInt(2, step);
	    	rs = pstmt_search.executeQuery();
	    	while(rs.next()) { 
	    		list.add(rs.getInt(1));
	    	}
	    	Collections.sort(list);

	    	Iterator<Integer> it = list.iterator();
	    	while(it.hasNext()) {
	    		PreparedStatement pstmt_update = null;
	    		Integer target = it.next();
	    		int target_step = target != null ? target : 0;
	    	    pstmt_update = conn.prepareStatement(update_sql);
	    	    pstmt_update.setInt(1, target_step - 1);
	    	    pstmt_update.setInt(2, recipe_id);
		    	pstmt_update.setInt(3, target_step);
		    	pstmt_update.executeUpdate();
		    	pstmt_update.close();
	    	}
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
			DBUtils.close(rs, pstmt_delete, conn);
			DBUtils.close(pstmt_search);
		}
	    
	    return ret;
	}
	
	/**
	 * Insert Recipe.
	 * 
	 * @param recipe Recipe Entity to be inserted.
	 * @return Primary Key of the inserted recipe.
	 */
	public static int insertRecipe(Recipe recipe) {
		Connection conn = DBUtils.getMySqlConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "insert into recipe(username,recipe_name,serve_num,preparation_time,cooking_time,image) values(?,?,?,?,?,?)";
	    int ret = 0;
	    
	    try {
	    	pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	    	pstmt.setString(1, recipe.getAuthor());
	        pstmt.setString(2, recipe.getRecipeName());
	        pstmt.setInt(3, recipe.getServeNum());
	        pstmt.setInt(4, recipe.getPreparationTime());
	        pstmt.setInt(5, recipe.getCookingTime());
	        // Insert the image to the database.
	        pstmt.setBlob(6, ImageUtils.imageToByte(recipe.getImage()));
	        ret = pstmt.executeUpdate();
	        
	        // Get the primary key of the recipe.
	        rs = pstmt.getGeneratedKeys();
	        if (rs.next()) {
	        	ret = rs.getInt(1);
	            recipe.setRecipeId(ret);
	        } 

	        // Insert ingredients.
	        List<Ingredient> ingList = recipe.getIngredients();
	        Iterator<Ingredient> ingIt = ingList.iterator();
	        while(ingIt.hasNext()) {
	        	DBController.insertIngredient(recipe.getRecipeId(), ingIt.next());
	        }
	        
	        // Insert preparation steps.
	        List<PreparationStep> stepList = recipe.getPreparationSteps();
	        Iterator<PreparationStep> stepIt = stepList.iterator();
	        int step = 1;
	        while(stepIt.hasNext()) {
	        	DBController.insertPreparationStep(recipe.getRecipeId(), step++, stepIt.next());
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	DBUtils.close(rs, pstmt, conn);
	    }
	    
	    return ret;
	}
	
	/**
	 * Update serve number.
	 * 
	 * @param recipe_id Id of Recipe Entity to be updated.
	 * @param num New number.
	 * @return Updated rows.
	 */
	public static int updateServeNum(int recipe_id, int num) {
		Connection conn = DBUtils.getMySqlConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "update recipe set serve_num=? where recipe_id=?";
	    int ret = 0;
	    
	    try {
	    	pstmt = conn.prepareStatement(sql);
	    	pstmt.setInt(1, num);
	    	pstmt.setInt(2, recipe_id);
	    	ret = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	DBUtils.close(rs, pstmt, conn);
	    }
	    
	    return ret;
	}
	
	/**
	 * Update preparation time.
	 * 
	 * @param recipe_id Id of Recipe Entity to be updated.
	 * @param time New time.
	 * @return Updated rows.
	 */
	public static int updatePrepTime(int recipe_id, int time) {
		Connection conn = DBUtils.getMySqlConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "update recipe set preparation_time=? where recipe_id=?";
	    int ret = 0;
	    
	    try {
	    	pstmt = conn.prepareStatement(sql);
	    	pstmt.setInt(1, time);
	    	pstmt.setInt(2, recipe_id);
	    	ret = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	DBUtils.close(rs, pstmt, conn);
	    }
	    
	    return ret;
	}
	
	/**
	 * Update cooking time.
	 * 
	 * @param recipe_id Id of Recipe Entity to be updated.
	 * @param time New time.
	 * @return Updated rows.
	 */
	public static int updateCookTime(int recipe_id, int time) {
		Connection conn = DBUtils.getMySqlConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "update recipe set cooking_time=? where recipe_id=?";
	    int ret = 0;
	    
	    try {
	    	pstmt = conn.prepareStatement(sql);
	    	pstmt.setInt(1, time);
	    	pstmt.setInt(2, recipe_id);
	    	ret = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	DBUtils.close(rs, pstmt, conn);
	    }
	    
	    return ret;
	}
	
	/**
	 * Updated image.
	 * 
	 * @param recipe_id Id of Recipe Entity to be updated.
	 * @param image New image.
	 * @return Updated rows.
	 */
	public static int updateImage(int recipe_id, Image image) {
		Connection conn = DBUtils.getMySqlConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "update recipe set image=? where recipe_id=?";
	    int ret = 0;
	    
	    try {
	    	pstmt = conn.prepareStatement(sql);
	    	pstmt.setBlob(1, ImageUtils.imageToByte(image));
	    	pstmt.setInt(2, recipe_id);
	    	ret = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	DBUtils.close(rs, pstmt, conn);
	    }
	    
	    return ret;
	}
	
	/**
	 * Insert User.
	 * Check the username with lower case.
	 * 
	 * @param user Inserted user.
	 * @return Updated rows; 0 means the username cannot be inserted because it already exists.
	 */
	public static int insertUser(User user) {
		Connection conn = DBUtils.getMySqlConn();
		PreparedStatement check_pstmt = null;
	    PreparedStatement insert_pstmt = null;
	    String check_sql = "select * from user where username=?";
	    String insert_sql = "insert into user(username,password) values(?,?)";
	    ResultSet rs = null;
	    int ret = 0;

	    try {
	    	check_pstmt = conn.prepareStatement(check_sql);
	        check_pstmt.setString(1, user.getUsername());
	        rs = check_pstmt.executeQuery();
	        // Username already exist.
	        if(rs.next()) {
	        	return ret;
	        }
	        
	        insert_pstmt = conn.prepareStatement(insert_sql);
	        insert_pstmt.setString(1, user.getUsername());
	        insert_pstmt.setString(2, user.getPassword());
	        ret = insert_pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	DBUtils.close(rs, check_pstmt, conn);
			DBUtils.close(insert_pstmt);
		}
	    
	    return ret;
	}
	
	/**
	 * Login User.
	 * 
	 * @param user Login user.
	 * @return Login success: username; login failure: null.
	 */
	public static String loginUser(User user) {
		Connection conn = DBUtils.getMySqlConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from user where username=? and password=?";
		String result = null;
		
		try {
	    	pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, user.getUsername());
	        pstmt.setString(2, user.getPassword());
	        rs = pstmt.executeQuery();
	        if(rs.next()) {
	        	result = rs.getString(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	DBUtils.close(rs, pstmt, conn);
		}
		
	    return result;
	}
	
	/**
	 * Get the list of ingredients according to the recipe id.
	 * 
	 * @param recipeId Recipe id.
	 * @return List of ingredients.
	 */
	public static List<Ingredient> getIngredientsOfRecipe(int recipeId){
		Connection conn = DBUtils.getMySqlConn();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select * from ingredient where recipe_id=?";
	    List<Ingredient> result = new ArrayList<>();
	    
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setObject(1, recipeId);
	        rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	Ingredient ing = new Ingredient(rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getString(5));
	        	result.add(ing);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
			DBUtils.close(rs, pstmt, conn);
		}
	    
	    return result;
	}
	
	/**
	 * Get the list of preparation steps according to the recipe id.
	 * 
	 * @param recipeId Recipe id.
	 * @return List of preparation steps.
	 */
	public static List<PreparationStep> getPreparationStepsOfRecipe(int recipeId){
		Connection conn = DBUtils.getMySqlConn();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select * from preparation_step where recipe_id=? order by step ASC";
	    List<PreparationStep> result = new ArrayList<>();
	    
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setObject(1, recipeId);
	        rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	PreparationStep step = new PreparationStep(rs.getInt(2),rs.getString(3));
	        	result.add(step);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
			DBUtils.close(rs, pstmt, conn);
		}
	    
	    return result;
	}
	
	/**
	 * Get the recipe by name.
	 * Must put the full recipe name.
	 * 
	 * @return Specified recipe.
	 */
	public static Recipe getRecipeByName(String name){
		Connection conn = DBUtils.getMySqlConn();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select * from recipe where recipe_name=?";
	    Recipe result = null;
	    
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setObject(1, name);
	        rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	result = new Recipe(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
	        	result.setImage(ImageUtils.byteToImage(rs.getBlob(7)));
	        	result.setIngredients(getIngredientsOfRecipe(rs.getInt(1)));
	        	result.setPreparationSteps(getPreparationStepsOfRecipe(rs.getInt(1)));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
			DBUtils.close(rs, pstmt, conn);
		}
	    
	    return result;
	}
	
	/**
	 * Get the recipe according to the recipe id.
	 * 
	 * @param id Recipe id.
	 * @return Recipe.
	 */
	public static Recipe getRecipeById(int id) {
		Connection conn = DBUtils.getMySqlConn();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select * from recipe where recipe_id=?";
	    Recipe result = null;
	    
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, id);
	        rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	result = new Recipe(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
	        	result.setImage(ImageUtils.byteToImage(rs.getBlob(7)));
	        	result.setIngredients(getIngredientsOfRecipe(rs.getInt(1)));
	        	result.setPreparationSteps(getPreparationStepsOfRecipe(rs.getInt(1)));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
			DBUtils.close(rs, pstmt, conn);
		}
	    
	    return result;
	}
	
	/**
	 * Get recipes by username.
	 * 
	 * @return A list of recipes.
	 */
	public static List<Recipe> getRecipeByUsername(String username){
		Connection conn = DBUtils.getMySqlConn();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select * from recipe where username=?";
	    List<Recipe> result = new ArrayList<>();
	    
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setObject(1, username);
	        rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	Recipe recipe = new Recipe(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
	        	recipe.setImage(ImageUtils.byteToImage(rs.getBlob(7)));
	        	recipe.setIngredients(getIngredientsOfRecipe(rs.getInt(1)));
	        	recipe.setPreparationSteps(getPreparationStepsOfRecipe(rs.getInt(1)));
	        	result.add(recipe);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
			DBUtils.close(rs, pstmt, conn);
		}
	    
	    return result;
	}
	
	/**
	 * Get all recipes.
	 * 
	 * @return A list of recipes.
	 */
	public static List<Recipe> getAllRecipes(){
		Connection conn = DBUtils.getMySqlConn();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select * from recipe";
	    List<Recipe> result = new ArrayList<>();
	    
	    try {
	        pstmt = conn.prepareStatement(sql);
	        rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	Recipe recipe = new Recipe(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
	        	recipe.setImage(ImageUtils.byteToImage(rs.getBlob(7)));
	        	recipe.setIngredients(getIngredientsOfRecipe(rs.getInt(1)));
	        	recipe.setPreparationSteps(getPreparationStepsOfRecipe(rs.getInt(1)));
	        	result.add(recipe);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
			DBUtils.close(rs, pstmt, conn);
		}
	    
	    return result;
	}
	
	/**
	 * Search for recipes by name.
	 * Case-insensitive.
	 * 
	 * @return A list of recipes.
	 */
	public static List<Recipe> searchRecipeByName(String search){
		Connection conn = DBUtils.getMySqlConn();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select * from recipe where recipe_name like ?";
	    List<Recipe> result = new ArrayList<>();
	    
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setObject(1, "%" + search + "%");
	        rs = pstmt.executeQuery();
	        while(rs.next()) {
	        	Recipe recipe = new Recipe(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
	        	recipe.setImage(ImageUtils.byteToImage(rs.getBlob(7)));
	        	recipe.setIngredients(getIngredientsOfRecipe(rs.getInt(1)));
	        	recipe.setPreparationSteps(getPreparationStepsOfRecipe(rs.getInt(1)));
	        	result.add(recipe);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
			DBUtils.close(rs, pstmt, conn);
		}
	    
	    return result;
	}
	
	/**
	 * Delete one recipe. 
	 * Also delete the related ingredients and preparation steps.
	 * 
	 * @param recipe Recipe to deleted.
	 * @return Updated rows.
	 */
	public static int deleteRecipe(Recipe recipe) {
		Connection conn = DBUtils.getMySqlConn();
	    PreparedStatement pstmt = null;
	    String sql = "delete from recipe where recipe_name=?";
	    int ret = 0;
	    
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, recipe.getRecipeName());
	        ret = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
			DBUtils.close(pstmt, conn);
		}
	    
	    return ret;
	}
	
	/**
	 * Main function to test.
	 * 
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		// Test searchRecipeByName().
		List<Recipe> list1 = searchRecipeByName("a");
        for(Recipe recipe : list1) {
        	System.out.println(recipe);
        }
        
        // Test insertUser().
        System.out.println(insertUser(new User("Alice", "970109")));
        
        // Test getRecipeByName().
        System.out.println(getRecipeByName("Hong Shao Rou"));
        
        // Test getRecipeByUsername().
		List<Recipe> list2 = getRecipeByUsername("alice");
        for(Recipe recipe : list2) {
        	System.out.println(recipe);
        }
        
        // Test getAllRecipes().
		List<Recipe> list3 = getAllRecipes();
        for(Recipe recipe : list3) {
        	System.out.println(recipe);
        }
	}
	
}
