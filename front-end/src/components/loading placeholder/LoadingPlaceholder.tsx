const LoadingPlaceholder = () => {
  return (
    <section className="space-y-2">
      {[...Array(5)].map((_, i) => (
        <section
          key={i}
          className="h-16 bg-gray-100 rounded animate-pulse"
        ></section>
      ))}
    </section>
  );
};

export default LoadingPlaceholder;
