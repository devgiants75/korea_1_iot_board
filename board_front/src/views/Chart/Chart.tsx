import React from "react";
import {
  ResponsiveContainer,
  Cell,
  PieChart,
  Pie,
  Tooltip
} from "recharts";

/*
! 차트 라이브러리
: Recharts
*/
interface DataProps {
  name: string;
  uv: number;
  pv: number;
  amt: number;
}

const data: DataProps[] = [
  { name: "Page A", uv: 4000, pv: 2400, amt: 2400 },
  { name: "Page B", uv: 3000, pv: 1398, amt: 2210 },
  { name: "Page C", uv: 2000, pv: 9800, amt: 2290 },
  { name: "Page D", uv: 2780, pv: 3908, amt: 2000 },
  { name: "Page E", uv: 1890, pv: 4800, amt: 2181 },
  { name: "Page F", uv: 2390, pv: 3800, amt: 2500 },
  { name: "Page G", uv: 3490, pv: 4300, amt: 2100 },
];

const data01 = [
  { name: "롯데 자이언츠", value: 500 },
  { name: "두산 베어스", value: 300 },
  { name: "기아 타이거즈", value: 300 },
  { name: "LG 트윈스", value: 200 },
  { name: "한화 이글스", value: 278 },
  { name: "NC 다이노스", value: 189 },
];

/*
! Recharts 사용하기

1. 설치 명령어: npm install recharts
  - 타입 에러 발생 시: npm install --save-dev @types/recharts

*/

const COLORS01 = [
  "#0088FE",
  "#00C49F",
  "#FFBB28",
  "#FF8042",
  "#AF19FF",
  "#FF2A66",
];

export default function Chart() {
  return (
    <div>
        <h1>2025 프로야구 우승팀 예측</h1>
        <PieChart width={730} height={250}>
          <Pie
            dataKey="value"
            // 차트 렌더링 시 애니메이션 효과 적용
            // : true가 기본값
            // : 없애고 싶으면 아래 코드 추가
            isAnimationActive={false}
            data={data01}

            // 차트의 정가운데 지표를 기준으로 x, y축 정렬
            cx="50%"
            cy="50%"

            // 차트의 크기 (원의 반지름)
            outerRadius={80}
            fill="#8884d8"
            label
          >
            {data01.map((entry, index) => (
              <Cell
                key={`cell-${index}`}
                fill={COLORS01[index % COLORS01.length]}
              />
            ))}
          </Pie>
          {/* 
            마우스 호버 시 해당 데이터를 보여주는 도구 
          */}
          <Tooltip />
        </PieChart>
    </div>
  );
}
