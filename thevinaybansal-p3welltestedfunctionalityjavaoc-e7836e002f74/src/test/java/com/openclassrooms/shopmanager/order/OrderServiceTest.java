package com.openclassrooms.shopmanager.order;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.openclassrooms.shopmanager.cart.CartService;
import com.openclassrooms.shopmanager.product.ProductEntity;
import com.openclassrooms.shopmanager.product.ProductService;


/**
 * Take this test method as a template to write your test methods for ProductService and OrderService.
 * A test method must check if a definite method does its job:
 *
 * Naming follows this popular convention : http://osherove.com/blog/2005/4/3/naming-standards-for-unit-tests.html
 */


@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
	
	@InjectMocks
    OrderService orderService;

    @Mock
    OrderRepository orderRepository;
    
    @Mock
    ProductService productService;
    
    @Mock
    CartService cartService;
    
    @Test
    public void addingProductToCart_returnTrue(){

        ProductEntity product = new ProductEntity();
        product.setId(1L);
       
        when(productService.getByProductId(1L)).thenReturn(product);
        
        boolean expectedResult = orderService.addToCart(product.getId());
        
        assertEquals(true, expectedResult); 
     
    }   

    @Test   
    public void saveOrder_returnSavedOrder() {
   	 
   	 Order order = new Order();
   	 
   	 ArgumentCaptor<Order> argument = ArgumentCaptor.forClass(Order.class);
   	 
   	 orderService.saveOrder(order);
   	 
   	 Mockito.verify(orderRepository).save(argument.capture());
   	 
   	 assertEquals(order, argument.getValue());
   	 
    }  
    
    @Test
    public void removeFromCart_returnEmptyCart() { 
   	 
   	 ProductEntity product1 = new ProductEntity();
   	 product1.setId(1L);
   	 
   	 when(productService.getByProductId(1L)).thenReturn(product1);
   	 
   	 orderService.addToCart(1L);
        orderService.removeFromCart(1L);
        
        boolean cartEmpty = orderService.isCartEmpty();

        assertEquals(true, cartEmpty);
   	  
    }
    
    @Test
    public void isCartEmpty_returnTrue() {
   	 
   	CartService cartList = new CartService();
   			
 		boolean removedFromCart = cartList.getCartLineList().isEmpty();
 		
 		assertEquals(true, removedFromCart); 
    
    }
    
    
}