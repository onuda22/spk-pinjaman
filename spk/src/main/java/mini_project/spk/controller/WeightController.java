package mini_project.spk.controller;

import lombok.RequiredArgsConstructor;
import mini_project.spk.model.Weight;
import mini_project.spk.service.WeightService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WeightController {
    private final WeightService weightService;

    @GetMapping("/weight-show")
    public List<Weight> getAll(){
        return weightService.getAll();
    }

    @PostMapping("/weight-create")
    public Weight create(@RequestBody Weight request){
        return weightService.create(request);
    }

    @PutMapping("/weight-edit/{id}")
    public Weight updateById(@PathVariable Integer id, @RequestBody Weight request){
        return weightService.updateById(id, request);
    }

    @DeleteMapping("/weight-delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id){
        weightService.deleteById(id);
    }
}
