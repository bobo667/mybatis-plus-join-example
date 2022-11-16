package icu.mhb.mpj.example.config;
import icu.mhb.mybatisplus.plugln.keyword.IFuncKeyWord;
import org.springframework.stereotype.Component;

/**
 * @author mahuibo
 * @Title: FuncKeyWordImpl
 * @email mhb0409@qq.com
 * @time 2022/11/16
 */
@Component
public class FuncKeyWordImpl implements IFuncKeyWord {

    @Override
    public String distinct() {
        return "distinct";
    }

}
