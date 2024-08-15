package mini_project.spk.service.implementation;

import lombok.RequiredArgsConstructor;
import mini_project.spk.model.Weight;
import mini_project.spk.repository.WeightRepo;
import mini_project.spk.service.WeightService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeightServiceImpl implements WeightService {
    private final WeightRepo weightRepo;

    @Override
    public List<Weight> getAll() {
        return weightRepo.findWeightAll();
    }

    @Override
    public Weight create(Weight request) {
        return weightRepo.saveWeight(
                request.getVariableName(),
                request.getWeight()
        );
    }

    @Override
    public Weight updateById(Integer id, Weight updateReq) {
        return weightRepo.updateWeight(
                id,
                updateReq.getVariableName(),
                updateReq.getWeight()
        );
    }

    @Override
    public void deleteById(Integer id) {
        weightRepo.deleteById(id);
    }
}
