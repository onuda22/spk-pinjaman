package mini_project.spk.controller;

import lombok.RequiredArgsConstructor;
import mini_project.spk.model.NormalizationParameter;
import mini_project.spk.service.NormalizationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NormalizationController {
    private final NormalizationService service;

    @GetMapping("/normal-params-show")
    public List<NormalizationParameter> getAll(){
        return service.getAll();
    }

    @PostMapping("/normal-params-create")
    public NormalizationParameter create(@RequestBody NormalizationParameter request){
        return service.create(request);
    }

    @PutMapping("/normal-params-edit/{id}")
    public NormalizationParameter updateById(@PathVariable Integer id, @RequestBody NormalizationParameter request){
        return service.updateById(id, request);
    }

    @DeleteMapping("/normal-params-delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id){
        service.deleteById(id);
    }
}
