import type { Session } from "../../services/SessionsService";

interface SessionCardProps {
  session: Session;
}

const SessionCard = ({ session }: SessionCardProps) => {
  return (
    <>
      <div className="flex justify-between gap-2 p-2 my-4 shadow-md shadow-orange-200/30 border border-gray-200 rounded">
        <article className="flex gap-4">
          <p>🛶</p>
          <p>{session.sessionName}</p>
          <p>{session.date}</p>
          <p>📒</p>
          <p>🪑🪑</p>
        </article>
        <article className="flex gap-2">
          <p className="font-medium text-red-600">20</p>
          <p className="font-medium text-blue-600">15</p>
        </article>
      </div>
      <div className="flex justify-between gap-2 p-2 my-4 shadow-md shadow-orange-200/30 border border-gray-200 rounded">
        <article className="flex gap-4">
          <p>🚀</p>
          <p>{session.sessionName}</p>
          <p>{session.date}</p>
          <p>📒</p>
          <p>🪑🪑</p>
        </article>
        <article className="flex gap-2">
          <p className="font-medium text-red-600">20</p>
          <p className="font-medium text-blue-600">15</p>
        </article>
      </div>
      <div className="flex justify-between gap-2 p-2 my-4 shadow-md shadow-orange-200/30 border border-gray-200 rounded">
        <article className="flex gap-4">
          <p>🏋🏿‍♀️</p>
          <p>{session.sessionName}</p>
          <p>{session.date}</p>
          <p>📒</p>
          <p>🪑🪑</p>
        </article>
        <article className="flex gap-2">
          <p className="font-medium text-red-600">20</p>
          <p className="font-medium text-blue-600">15</p>
        </article>
      </div>
    </>
  );
};

export default SessionCard;
