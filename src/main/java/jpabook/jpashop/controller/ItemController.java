package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Entity.Items.Book.Book;
import jpabook.jpashop.domain.Entity.Items.Item;
import jpabook.jpashop.domain.service.ItemService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model){
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm form){
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model){
        List<Item> item = itemService.findItem();
        model.addAttribute("items", item);
        return "items/itemList";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model){
        Book book = (Book)itemService.findOne(itemId);
        BookForm bookForm = new BookForm();
        bookForm.setId(book.getId());
        bookForm.setAuthor(book.getAuthor());
        bookForm.setIsbn(book.getIsbn());
        bookForm.setName(book.getName());
        bookForm.setPrice(book.getPrice());
        bookForm.setStockQuantity(bookForm.getStockQuantity());

        model.addAttribute("form", bookForm);
        return "items/updateItemForm";
    }

    @PostMapping("items/{itemId}/edit")
    public String updateItemForm(@ModelAttribute("form")BookForm form){
        Book book = new Book();
        book.setId(form.getId());
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/";
    }
}
