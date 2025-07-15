import { useState } from "react";
import type { Athlete } from "../../services/AthleteService";

interface AthleteCardProps {
  athlete: Athlete;
  sessionId: number;
}

const AthleteCard = ({ athlete, sessionId }: AthleteCardProps) => {
  const isInTheList = athlete.attendedSessionIds?.includes(sessionId);
  const [present, setPresent] = useState(isInTheList);

  //console.log(athlete);

  const handleEdit = () => {};
  const toggleIsPresent = () => {
    console.log(athlete.firstName, present);
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
        </article>

        <article
          onClick={toggleIsPresent}
          className="flex justify-center items-center gap-2 pl-10 text-2xl"
        >
          {present ? <p>✅</p> : <p>☑️</p>}
        </article>
      </div>
    </>
  );
};

export default AthleteCard;
