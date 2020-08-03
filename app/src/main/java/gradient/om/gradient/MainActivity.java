package gradient.om.gradient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import om.gradient.GradientTextView;
import om.gradient.RoundRainbowRelayoutLayout;

public class MainActivity extends AppCompatActivity {
    private GradientTextView gradientTextView;
    private RoundRainbowRelayoutLayout roundRainbowRelayoutLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gradientTextView=findViewById(R.id.gtv);
        roundRainbowRelayoutLayout=findViewById(R.id.rrrl);

        gradientTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gradientTextView.isSelected()){
                    gradientTextView.setGtvSelected(false);
                }else {
                    gradientTextView.setGtvSelected(true);
                }
            }
        });

        roundRainbowRelayoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (roundRainbowRelayoutLayout.isSelected()){
                    roundRainbowRelayoutLayout.setRrvSelected(false);
                }else {
                    roundRainbowRelayoutLayout.setRrvSelected(true);
                }
            }
        });
    }
}
