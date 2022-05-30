package com.ycao.automobile.controller.admin;

import com.ycao.automobile.controller.BaseController;
import com.ycao.automobile.model.ProductDomain;
import com.ycao.automobile.model.command.CommandDomain;
import com.ycao.automobile.service.ICommandService;
import com.ycao.automobile.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {

    @Autowired
    IHomeService iHomeService;
    @Autowired
    ICommandService iCommandService;

    @GetMapping(value = "/myCommand")
    public String toRegister(HttpServletRequest request){

        List<ProductDomain> highNoteProducts = iHomeService.getHighNoteProductHasComments(4);
        List<ProductDomain> latestProducts = iHomeService.getLatestProductHasComments(5);
        List<CommandDomain> commandDomains = iCommandService.getAllCommandFromUser(getCurrentUserId(request));
        request.setAttribute("allCommands", commandDomains);
        request.setAttribute("latestProducts", latestProducts);
        request.setAttribute("highNoteProducts", highNoteProducts);
        return "admin/myCommand";
    }
}
