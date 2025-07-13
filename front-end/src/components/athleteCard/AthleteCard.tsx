import { useState } from "react";
import type { Athlete } from "../../services/AthleteService";

interface AthleteCardProps {
  athlete: Athlete;
}

const AthleteCard = ({ athlete }: AthleteCardProps) => {
  const [present, setPresent] = useState(false);

  const handleEdit = () => {};
  const handleIsPresent = () => {
    setPresent(!present);
  };

  return (
    <>
      <div className="flex justify-between gap-2 p-2 my-1 shadow-md shadow-orange-200/30 border border-gray-200 rounded min-w-lg">
        <article className="flex gap-4">
          <section className="flex justify-center items-center gap-2 text-4xl">
            {athlete.gender === "FEMALE" ? <p>🙍‍♀️</p> : <p>🙎‍♂️</p>}
          </section>
          <article onClick={handleEdit} className="flex gap-2">
            <section className="flex flex-col">
              <article className="flex gap-2">
                <p className="text-2xl text-orange-500">{athlete.nickName}</p>
              </article>
              <p className="italic text-gray-600">
                {athlete.firstName} {athlete.lastName}
              </p>
            </section>
          </article>
          {/* <section className="flex justify-center items-center gap-2">
                <p onClick={handleTrainingPlan} className="text-3xl">
                  📒
                </p>
                <p onClick={handleSeatingPlan} className="flex text-3xl">
                  <span>🪑</span>
                  <span className="-ml-2">🪑</span>
                </p>
              </section> */}
        </article>
        <article
          onClick={handleIsPresent}
          className="flex justify-center items-center gap-2 pl-10 text-2xl"
        >
          {present ? <p>✅</p> : <p>☑️</p>}
        </article>
        {/* <article
              onClick={handleAttendance}
              className="flex justify-center items-center gap-2 pl-10 text-2xl"
            >
              <p className="font-medium text-red-600">
                {
                  session.presentAthletes?.filter((el) => el.gender === "FEMALE")
                    .length
                }
              </p>
              <p className="font-medium text-blue-600">
                {
                  session.presentAthletes?.filter((el) => el.gender === "MALE")
                    .length
                }
              </p>
              <p onClick={handleDelete} className="text-2xl pl-2">
                🗑️
              </p>
            </article> */}
      </div>
    </>
  );
};

export default AthleteCard;
