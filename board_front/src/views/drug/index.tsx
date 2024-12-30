import React, { useState } from 'react'

interface DrugShape {
  id: number;
  name: string;
}

const drugShapes: DrugShape[] = [
  { id: 1, name: '원형' },
  { id: 2, name: '타원형' },
  { id: 3, name: '반원형' },
  { id: 4, name: '삼각형' },
  { id: 5, name: '사각형' },
  { id: 6, name: '마름모형' },
];

export default function Index() {
  const [selectedShape, setSelectedShape] = useState<DrugShape | null>(null);

  const handleDrugShapeClick = (drugShape: DrugShape) => {
    setSelectedShape(drugShape);
  }

  return (
    <>
      <div>
        {drugShapes.map(shape => (
          <div
            key={shape.id}
            onClick={() => handleDrugShapeClick(shape)}
          >
            {shape.name}
          </div>
        ))}
      </div>
      <hr />
      <div>
        {selectedShape && (
          <div>
            선택한 모양: {selectedShape.name} / 선택한 ID: {selectedShape.id}
          </div>
        )}
      </div>
    </>
  )
}
