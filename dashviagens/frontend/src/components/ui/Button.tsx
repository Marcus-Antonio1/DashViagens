import type { ButtonHTMLAttributes } from "react";

type ButtonProps = ButtonHTMLAttributes<HTMLButtonElement>;

export function Button(props: ButtonProps) {
  return (
    <button
      {...props}
      style={{
        background: "var(--color-primary)",
        color: "#fff",
        border: "none",
        borderRadius: "var(--radius-sm)",
        padding: "12px 20px",
        cursor: "pointer",
        fontWeight: 600,
      }}
    />
  );
}