import type { Session } from "../services/SessionsService";
import SessionCard from "./sessionCard/SessionCard";

interface SessionListProps {
  sessions: Session[];
}

const SessionList = ({ sessions }: SessionListProps) => {
  if (sessions.length === 0) {
    return null;
  }
  console.log(sessions);
  return (
    <>
      {sessions.map((session) => (
        <SessionCard key={session.id} session={session} />
      ))}
    </>
  );
};

export default SessionList;
