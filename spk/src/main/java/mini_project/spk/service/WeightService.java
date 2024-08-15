package mini_project.spk.service;

import mini_project.spk.model.NormalizationParameter;
import mini_project.spk.model.Weight;

import java.util.List;

public interface WeightService {
    List<Weight> getAll();
    Weight create(Weight request);
    Weight updateById(Integer id, Weight updateReq);
    void deleteById(Integer id);
}
